package com.epam.xsl.parser;


public class ProductsXMLElement {
	private static final String PREFIX = "pr:";

	public static final String CATEGORY_ELEM = PREFIX + "category";
	public static final String SUBCATEGORY_ELEM = PREFIX + "subcategory";
	public static final String GOOD_ELEM = PREFIX + "good";
	public static final String PRODUCER_ELEM = PREFIX + "producer";
	public static final String MODEL_ELEM = PREFIX + "model";
	public static final String DATE_OF_ISSUE_ELEM = PREFIX + "date-of-issue";
	public static final String COLOR_ELEM = PREFIX + "color";
	public static final String PRICE_ELEM = PREFIX + "price";
	public static final String NOT_IN_STOCK_ELEM = PREFIX + "not-in-stock";

	private ProductsXMLElement() {
	}

	public static boolean isCategoryElement(String qName) {
		return CATEGORY_ELEM.equals(qName);
	}

	public static boolean isSubcategoryElement(String qName) {
		return SUBCATEGORY_ELEM.equals(qName);
	}

	public static boolean isGoodElement(String qName) {
		return GOOD_ELEM.equals(qName);
	}

	public static boolean isProducerElement(String qName) {
		return PRODUCER_ELEM.equals(qName);
	}

	public static boolean isModelElement(String qName) {
		return MODEL_ELEM.equals(qName);
	}

	public static boolean isDateOfIssueElement(String qName) {
		return DATE_OF_ISSUE_ELEM.equals(qName);
	}

	public static boolean isColorElement(String qName) {
		return COLOR_ELEM.equals(qName);
	}

	public static boolean isPriceElement(String qName) {
		return PRICE_ELEM.equals(qName);
	}

	public static boolean isNotInStockElement(String qName) {
		return NOT_IN_STOCK_ELEM.equals(qName);
	}
}
