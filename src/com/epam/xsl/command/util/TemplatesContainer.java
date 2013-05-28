package com.epam.xsl.command.util;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class TemplatesContainer {
	private static volatile TemplatesContainer container;

	private static final Object lock = new Object();

	private static final TransformerFactory transformerFactory = TransformerFactory
			.newInstance();

	public final Templates CATEGORIES_XSLT;
	public final Templates SUBCATEGORIES_XSLT;
	public final Templates GOODS_XSLT;

	private TemplatesContainer() throws TransformerConfigurationException {
		try {
			CATEGORIES_XSLT = transformerFactory.newTemplates(new StreamSource(
					FileURLContainer.CATEGORIES_XSLT));
			SUBCATEGORIES_XSLT = transformerFactory
					.newTemplates(new StreamSource(
							FileURLContainer.SUBCATEGORIES_XSLT));
			GOODS_XSLT = transformerFactory.newTemplates(new StreamSource(
					FileURLContainer.GOODS_XSLT));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static TemplatesContainer getInstance()
			throws TransformerConfigurationException {
		if (container == null) {
			synchronized (lock) {
				if (container == null) {
					container = new TemplatesContainer();
				}
			}
		}
		return container;
	}
}
