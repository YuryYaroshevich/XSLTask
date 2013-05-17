package com.epam.xsl.parser.dom;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.epam.xsl.parser.ProductsXMLParser;
import com.epam.xsl.parser.exception.ParserLogicalException;
import com.epam.xsl.parser.exception.ParserTechnicalException;
import com.epam.xsl.product.Category;

public class ProductsXMLDOMParser implements ProductsXMLParser {
	private static final DocumentBuilderFactory docBuilderfactory = DocumentBuilderFactory
			.newInstance();

	private final DocumentBuilder docBuilder;
	
	private static final ProductsDOMHandler handler = new ProductsDOMHandler();
	
	public ProductsXMLDOMParser() throws ParserTechnicalException {
		try {
			docBuilder = docBuilderfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new ParserTechnicalException(e);
		}
	}
	
	public List<Category> parse(String xml) throws ParserLogicalException,
			ParserTechnicalException {
		try {
			Document document = docBuilder.parse(xml);
			Element root = document.getDocumentElement();
			List<Category> categories = handler.getCategories(root);
			return categories;
		} catch (SAXException e) {
			e.printStackTrace();
			throw new ParserLogicalException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ParserTechnicalException(e);
		}
	}

}
