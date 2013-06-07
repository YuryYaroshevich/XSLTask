package com.epam.xsl.command.util;

import static com.epam.xsl.command.util.FileURLContainer.PRODUCTS_XML;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;
import static com.epam.xsl.command.util.ProductsXMLElement.CATEGORY_ELEM;
import static com.epam.xsl.command.util.ProductsXMLElement.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.epam.xsl.product.Good;

public final class XMLWrapper {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(
			true);
	private static final Lock readLock = lock.readLock();
	private static final Lock writeLock = lock.writeLock();

	private static final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
			.newInstance();

	private XMLWrapper() {
	}

	public static Document readFromXML() throws Exception {
		readLock.lock();
		try {
			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
			return builder.parse(getFileURL(PRODUCTS_XML));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			readLock.unlock();
		}
	}

	public static Document writeToXML(String categName, String subcategName,
			Good good) throws Exception {
		writeLock.lock();
		try {
			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
			Document document = builder.parse(getFileURL(PRODUCTS_XML));
			NodeList categNodes = document.getElementsByTagName(CATEGORY_ELEM);
			for (int ci = 0; ci < categNodes.getLength(); ci++) {
				Element categElem = (Element) categNodes.item(ci);
				String nameAttrVal = categElem.getAttribute(NAME_ATTR);
				if (categName.equals(nameAttrVal)) {
					addGoodToAppropriateCateg(document, categElem, nameAttrVal,
							good);
					break;
				}
			}
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			writeLock.unlock();
		}
	}

	private static void addGoodToAppropriateCateg(Document document,
			Element categElem, String subcategName, Good good) {
		NodeList subcategNodes = categElem
				.getElementsByTagName(SUBCATEGORY_ELEM);
		addGoodToAppropriateSubcateg(document, subcategNodes, subcategName,
				good);
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
		producerElem.setNodeValue(good.getProducer());
		newGoodElem.appendChild(producerElem);
	}

	private static void appendModel(Document document, Element newGoodElem,
			Good good) {
		Element modelElem = document.createElement(MODEL_ELEM);
		modelElem.setNodeValue(good.getModel());
		newGoodElem.appendChild(modelElem);
	}

	private static void appendDateOfIssue(Document document,
			Element newGoodElem, Good good) {
		Element dateOfIssueElem = document.createElement(DATE_OF_ISSUE_ELEM);
		dateOfIssueElem.setNodeValue(good.getDateOfIssue());
		newGoodElem.appendChild(dateOfIssueElem);
	}

	private static void appendColor(Document document, Element newGoodElem,
			Good good) {
		Element colorElem = document.createElement(COLOR_ELEM);
		colorElem.setNodeValue(good.getColor());
		newGoodElem.appendChild(colorElem);
	}

	private static void appendShoppingState(Document document,
			Element newGoodElem, Good good) {
		if (good.isNotInStock()) {
			Element notInStockElem = document.createElement(NOT_IN_STOCK_ELEM);
			newGoodElem.appendChild(notInStockElem);
		} else {
			Element priceElem = document.createElement(PRICE_ELEM);
			Integer price = good.getPrice();
			priceElem.setNodeValue(price.toString());
		}
	}
}
