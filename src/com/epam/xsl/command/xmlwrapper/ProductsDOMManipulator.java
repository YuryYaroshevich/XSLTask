package com.epam.xsl.command.xmlwrapper;

import static com.epam.xsl.appconstant.AppConstant.PRODUCTS_XML;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.CATEGORY_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.COLOR_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.DATE_OF_ISSUE_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.GOOD_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.MODEL_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.NAME_ATTR;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.NOT_IN_STOCK_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.PRICE_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.PRODUCER_ELEM;
import static com.epam.xsl.command.xmlwrapper.ProductsXMLElement.SUBCATEGORY_ELEM;
import static com.resource.PropertyGetter.getProperty;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.xsl.product.Good;

final class ProductsDOMManipulator {
	private ProductsDOMManipulator() {
	}

	public static void addGoodToDocument(String categName, String subcategName,
			Good good, Document document) {
		NodeList categNodes = document.getElementsByTagName(CATEGORY_ELEM);
		for (int ci = 0; ci < categNodes.getLength(); ci++) {
			Element categElem = (Element) categNodes.item(ci);
			String nameAttrVal = categElem.getAttribute(NAME_ATTR);
			if (categName.equals(nameAttrVal)) {
				NodeList subcategNodes = categElem
						.getElementsByTagName(SUBCATEGORY_ELEM);
				addGoodToAppropriateSubcateg(document, subcategNodes,
						subcategName, good);
				break;
			}
		}
	}

	private static void addGoodToAppropriateSubcateg(Document document,
			NodeList subcategNodes, String subcategName, Good good) {
		for (int sj = 0; sj < subcategNodes.getLength(); sj++) {
			Element subcategElem = (Element) subcategNodes.item(sj);
			String nameAttrVal = subcategElem.getAttribute(NAME_ATTR);
			if (subcategName.equals(nameAttrVal)) {
				Element newGoodElem = buildNewGoodElem(document, good);
				subcategElem.appendChild(newGoodElem);
				break;
			}
		}
	}

	private static Element buildNewGoodElem(Document document, Good good) {
		Element newGoodElem = document.createElement(GOOD_ELEM);
		appendProducer(document, newGoodElem, good);
		appendModel(document, newGoodElem, good);
		appendDateOfIssue(document, newGoodElem, good);
		appendColor(document, newGoodElem, good);
		appendShoppingState(document, newGoodElem, good);
		return newGoodElem;
	}

	private static void appendProducer(Document document, Element newGoodElem,
			Good good) {
		Element producerElem = document.createElement(PRODUCER_ELEM);
		Node text = document.createTextNode(good.getProducer());
		producerElem.appendChild(text);
		newGoodElem.appendChild(producerElem);
	}

	private static void appendModel(Document document, Element newGoodElem,
			Good good) {
		Element modelElem = document.createElement(MODEL_ELEM);
		Node text = document.createTextNode(good.getModel());
		modelElem.appendChild(text);
		newGoodElem.appendChild(modelElem);
	}

	private static void appendDateOfIssue(Document document,
			Element newGoodElem, Good good) {
		Element dateOfIssueElem = document.createElement(DATE_OF_ISSUE_ELEM);
		Node text = document.createTextNode(good.getDateOfIssue());
		dateOfIssueElem.appendChild(text);
		newGoodElem.appendChild(dateOfIssueElem);
	}

	private static void appendColor(Document document, Element newGoodElem,
			Good good) {
		Element colorElem = document.createElement(COLOR_ELEM);
		Node text = document.createTextNode(good.getColor());
		colorElem.appendChild(text);
		newGoodElem.appendChild(colorElem);
	}

	private static void appendShoppingState(Document document,
			Element newGoodElem, Good good) {
		if (good.isNotInStock()) {
			Element notInStockElem = document.createElement(NOT_IN_STOCK_ELEM);
			newGoodElem.appendChild(notInStockElem);
		} else {
			Element priceElem = document.createElement(PRICE_ELEM);
			Node text = document.createTextNode(good.getPrice());
			priceElem.appendChild(text);
			newGoodElem.appendChild(priceElem);
		}
	}

	public static class ProductsDocumentWrapper {
		private Document productsDoc;
		private long lastModified;

		private final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();

		public Document getProductsDocument()
				throws ParserConfigurationException, SAXException, IOException {
			String productsXMLURL = getProperty(PRODUCTS_XML);
			File productsXML = new File(productsXMLURL);
			long lastModified = productsXML.lastModified();
			// Parse XML if productsDoc is null or out of date
			if (productsDoc == null || this.lastModified < lastModified) {
				DocumentBuilder builder = docBuilderFactory
						.newDocumentBuilder();
				productsDoc = builder.parse(productsXMLURL);
				this.lastModified = lastModified;
			}
			return productsDoc;
		}
	}
}
