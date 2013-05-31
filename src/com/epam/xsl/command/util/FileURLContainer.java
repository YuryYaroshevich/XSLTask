package com.epam.xsl.command.util;

import java.util.ResourceBundle;

public class FileURLContainer {
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("com.resource.fileurl");
	
	public static final String PRODUCTS_XML = "products.xml";
	public static final String CATEGORIES_XSLT = "categories.xslt";
	public static final String SUBCATEGORIES_XSLT = "subcategories.xslt";
	public static final String GOODS_XSLT = "goods.xslt";

	private FileURLContainer() {
	}

	public static String getFileURL(String key) {
		return bundle.getString(key);
	}
}
