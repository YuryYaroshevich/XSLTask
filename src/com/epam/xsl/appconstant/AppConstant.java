package com.epam.xsl.appconstant;

public final class AppConstant {
	private AppConstant() {
	}

	// keys for reading URLs of XML and XSLT files from properties
	public static final String PRODUCTS_XML = "products.xml";
	public static final String CATEGORIES_XSLT = "categories.xslt";
	public static final String SUBCATEGORIES_XSLT = "subcategories.xslt";
	public static final String GOODS_XSLT = "goods.xslt";
	public static final String ADD_GOOD_XSLT = "add.good.xslt";
	public static final String VALIDATION_XSLT = "validation.xslt";
	public static final String SAVE_GOOD_XSLT = "save.good.xslt";

	// I need this part of URL to build an address for redirecting
	public static final String REDIRECT_QUERY_START = "redirect.query.start";

	// parameter names for getting values from request or error message keys
	public static final String CATEGORY_NAME = "categoryName";
	public static final String SUBCATEGORY_NAME = "subcategoryName";
	public static final String PRODUCER = "producer";
	public static final String MODEL = "model";
	public static final String DATE_OF_ISSUE = "dateOfIssue";
	public static final String COLOR = "color";
	public static final String PRICE = "price";
	public static final String NOT_IN_STOCK = "notInStock";
}
