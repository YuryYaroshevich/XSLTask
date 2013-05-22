package com.epam.xsl.command.util;

import java.io.File;

// contains XSLT file objects
public class XSLTContainer {
	private XSLTContainer() {
	}

	public static final File CATEGORIES_XSLT = new File(
			"C:/workspace/XSLTask/WebContent/WEB-INF/xslt/Categories.xslt");

	public static final File SUBCATEGORIES_XSLT = new File(
			"C:/workspace/XSLTask/WebContent/WEB-INF/xslt/Subcategories.xslt");
}
