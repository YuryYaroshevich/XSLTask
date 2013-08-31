package com.epam.xsl.constant;

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

	// keys for error messages
	public static final String EMPTY_PRODUCER = "empty.producer";
	public static final String EMPTY_MODEL = "empty.model";
	public static final String WRONG_MODEL_FORMAT = "wrong.model.format";
	public static final String EMPTY_DATE = "empty.date";
	public static final String WRONG_DATE_FORMAT = "wrong.date.format";
	public static final String WRONG_DATE_RANGE = "wrong.date.range";
	public static final String EMPTY_COLOR = "empty.color";
	public static final String NOT_IN_STOCK_TO_TRUE = "not.in.stock.to.true";
	public static final String NOT_IN_STOCK_TO_FALSE = "not.in.stock.to.false";
	public static final String WRONG_PRICE_FORMAT = "wrong.price.format";

	// parameter names for taking values from request
	public static final String CATEGORY_NAME = "categoryName";
	public static final String SUBCATEGORY_NAME = "subcategoryName";
	
	// parameter name for setting validator in transformer
	public static final String VALIDATOR = "validator";
}
