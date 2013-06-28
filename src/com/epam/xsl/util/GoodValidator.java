package com.epam.xsl.util;

import static com.epam.xsl.appconstant.AppConstant.*;
import static com.resource.PropertyGetter.getProperty;

import java.util.regex.Pattern;

import com.resource.PropertyGetter.BundleType;

public final class GoodValidator {
	// I return empty string when everything is valid
	private static final String VALID = "";

	private static final String MODEL_REGEXP = "[a-zA-Z]{2}\\d{3}";
	private static final String DATE_REGEXP =
			"(0[1-9]|1\\d|2\\d|3[01])-(0[1-9]|1[0-2])-(\\d{4})";
	private static final String CORRECT_DATE_RANGE_REGEXP = 
			"(0[1-9]|1\\d|2\\d|3[01])-(0[1-9]|1[0-2])-(19\\d{2}|2\\d{3})";
	private static final String NUMBER_REGEXP = "\\d+";

	private static final BundleType ERROR_MSG_BUNDLE = BundleType.ERROR_MSG_BUNDLE;

	private GoodValidator() {
	}

	public static String validateProducer(String producer) {
		if (producer.isEmpty()) {
			return getProperty(EMPTY_PRODUCER, ERROR_MSG_BUNDLE);
		}
		return VALID;
	}

	public static String validateModel(String model) {
		if (model.isEmpty()) {
			return getProperty(EMPTY_MODEL, ERROR_MSG_BUNDLE);
		} else if (!Pattern.matches(MODEL_REGEXP, model)) {
			return getProperty(WRONG_MODEL_FORMAT, ERROR_MSG_BUNDLE);
		}
		return VALID;
	}

	public static String validateDate(String date) {
		if (date.isEmpty()) {
			return getProperty(EMPTY_DATE, ERROR_MSG_BUNDLE);
		} else if (Pattern.matches(DATE_REGEXP, date)) {
			if (!Pattern.matches(CORRECT_DATE_RANGE_REGEXP, date)) {
				return getProperty(WRONG_DATE_RANGE, ERROR_MSG_BUNDLE);
			}
		} else {
			return getProperty(WRONG_DATE_FORMAT, ERROR_MSG_BUNDLE);
		}
		return VALID;
	}

	public static String validateColor(String color) {
		if (color.isEmpty()) {
			return getProperty(EMPTY_COLOR, ERROR_MSG_BUNDLE);
		}
		return VALID;
	}

	// Checks price and if it empty, checks notInStock to be true.
	// If price is correct then notInStock should be false.
	public static String validateShopState(String price, String notInStock) {
		if (price.isEmpty()) {
			// then notInStock should be true
			if (!Boolean.valueOf(notInStock)) {
				return getProperty(NOT_IN_STOCK_TO_TRUE, ERROR_MSG_BUNDLE);
			}
		} else if (Pattern.matches(NUMBER_REGEXP, price)) {
			// then notInStock should be false
			if (Boolean.valueOf(notInStock)) {
				return getProperty(NOT_IN_STOCK_TO_FALSE, ERROR_MSG_BUNDLE);
			}
		} else {
			return getProperty(WRONG_PRICE_FORMAT, ERROR_MSG_BUNDLE);
		}
		return VALID;
	}

	public static boolean isGoodValid(String msgAboutProducer,
			String msgAboutModel, String msgAboutDate, String msgAboutColor,
			String msgAboutShopState) {
		if (!VALID.equals(msgAboutProducer)) {
			return false;
		} else if (!VALID.equals(msgAboutModel)) {
			return false;
		} else if (!VALID.equals(msgAboutDate)) {
			return false;
		} else if (!VALID.equals(msgAboutColor)) {
			return false;
		} else if (!VALID.equals(msgAboutShopState)) {
			return false;
		}
		return true;
	}
}
