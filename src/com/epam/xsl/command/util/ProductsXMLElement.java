package com.epam.xsl.command.util;

final class ProductsXMLElement {
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
	
	public static final String NAME_ATTR = "name";
	
	private ProductsXMLElement() {
	}
}
