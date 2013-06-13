package com.epam.xsl.command.util;

import static com.epam.xsl.command.util.FileURLContainer.PRODUCTS_XML;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

final class ProductsDocumentWrapper {
	private Document productsDoc;
	private long lastModified;

	private final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
			.newInstance();

	public Document getProductsDocument()
			throws ParserConfigurationException, SAXException, IOException {
		String productsXMLURL = getFileURL(PRODUCTS_XML);
		File productsXML = new File(productsXMLURL);
		long lastModified = productsXML.lastModified();
		// Parse XML if productsDoc is null or out of date 
		if (productsDoc == null ||
				this.lastModified < lastModified) {
			DocumentBuilder builder = docBuilderFactory
					.newDocumentBuilder();
			productsDoc = builder.parse(productsXMLURL);
			this.lastModified = lastModified;
		}
		return productsDoc;
	}
}
