package com.epam.xsl.util;

import static com.epam.xsl.constant.AppConstant.*;
import static com.epam.xsl.resource.PropertyGetter.getProperty;

import java.util.regex.Pattern;

public final class GoodValidator {
	private boolean isGoodValid;	
	
	// I return empty string when everything is valid
	private static final String VALID = "";

	private static final String MODEL_REGEXP = "^[a-zA-Z]{2}\\d{3}$";
	private static final String DATE_REGEXP =
			"^(0[1-9]|1\\d|2\\d|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
	private static final String CORRECT_DATE_RANGE_REGEXP = 
			"^(0[1-9]|1\\d|2\\d|3[01])-(0[1-9]|1[0-2])-(19\\d{2}|2\\d{3})$";
	private static final String NUMBER_REGEXP = "^\\d+$";


	public GoodValidator() {
		setGoodValid(true);
	}
	
	public void setGoodValid(boolean isValid) {
		this.isGoodValid = isValid;
	}
	
	public void reset() {
		setGoodValid(true);
	}

	public String validateProducer(String producer) {
		System.out.println("xer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		if (producer.isEmpty()) {
			setGoodValid(false);
			return getProperty(EMPTY_PRODUCER);
		}		
		return VALID;
	}

	public String validateModel(String model) {
		System.out.println("xer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		if (model.isEmpty()) {
			setGoodValid(false);
			return getProperty(EMPTY_MODEL);
		} else if (!Pattern.matches(MODEL_REGEXP, model)) {
			setGoodValid(false);
			return getProperty(WRONG_MODEL_FORMAT);
		}
		return VALID;
	}

	public String validateDate(String date) {
		System.out.println("xer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		if (date.isEmpty()) {
			setGoodValid(false);
			return getProperty(EMPTY_DATE);
		} else if (Pattern.matches(DATE_REGEXP, date)) {
			if (!Pattern.matches(CORRECT_DATE_RANGE_REGEXP, date)) {
				setGoodValid(false);
				return getProperty(WRONG_DATE_RANGE);
			}
		} else {
			setGoodValid(false);
			return getProperty(WRONG_DATE_FORMAT);
		}
		return VALID;
	}

	public String validateColor(String color) {
		System.out.println("xer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		if (color.isEmpty()) {
			setGoodValid(false);
			return getProperty(EMPTY_COLOR);
		}
		return VALID;
	}

	// Checks price and if it empty, checks notInStock to be true.
	// If price is correct then notInStock should be false.
	public String validateShopState(String price, String notInStock) {
		System.out.println("xer!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		if (price.isEmpty()) {
			// then notInStock should be true
			if (!Boolean.valueOf(notInStock)) {
				setGoodValid(false);
				return getProperty(NOT_IN_STOCK_TO_TRUE);
			}
		} else if (Pattern.matches(NUMBER_REGEXP, price)) {
			// then notInStock should be false
			if (Boolean.valueOf(notInStock)) {
				setGoodValid(false);
				return getProperty(NOT_IN_STOCK_TO_FALSE);
			}
		} else {
			setGoodValid(false);
			return getProperty(WRONG_PRICE_FORMAT);
		}
		return VALID;
	}
	
	/*String msgAboutProducer,
	String msgAboutModel, String msgAboutDate, String msgAboutColor,
	String msgAboutShopState*/

	public boolean isGoodValid() {
		/*if (!VALID.equals(msgAboutProducer)) {
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
		return true;*/
		System.out.println(isGoodValid);
		return isGoodValid;
	}
}
