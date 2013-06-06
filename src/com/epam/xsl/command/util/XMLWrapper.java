package com.epam.xsl.command.util;

import static com.epam.xsl.command.util.FileURLContainer.PRODUCTS_XML;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import org.w3c.dom.Document;

public final class XMLWrapper {
	private static final String XML_URL = getFileURL(PRODUCTS_XML);
	
	private XMLWrapper() {	
	}
	
	public static Document readXML() {
		return null;
	}
	
	public static Document writeXML() {
		return null;
	}
}
