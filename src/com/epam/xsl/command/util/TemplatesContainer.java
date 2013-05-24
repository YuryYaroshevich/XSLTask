package com.epam.xsl.command.util;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class TemplatesContainer {
	private TemplatesContainer() {
	}

	private static final TransformerFactory transformerFactory = TransformerFactory
			.newInstance();

	private static final String CATEGORIES_XSLT =
			"C:/workspace/XSLTask/WebContent/WEB-INF/xslt/products/Categories.xslt";
	private static final String SUBCATEGORIES_XSLT =
			"C:/workspace/XSLTask/WebContent/WEB-INF/xslt/products/Subcategories.xslt";
	private static final String GOODS_XSLT =
			"C:/workspace/XSLTask/WebContent/WEB-INF/xslt/products/Goods.xslt";

	public static final Templates CATEGORY_TEMPL;
	
	static {
		// initialize category template
		Source styleSource = new StreamSource(new File(CATEGORIES_XSLT));
		CATEGORY_TEMPL = transformerFactory.newTemplates(styleSource);
	}
}
