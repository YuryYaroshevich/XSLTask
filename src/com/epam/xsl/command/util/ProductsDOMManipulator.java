package com.epam.xsl.command.util;

import static com.epam.xsl.command.util.ProductsXMLElement.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.epam.xsl.product.Good;

final class ProductsDOMManipulator {
	private ProductsDOMManipulator() {
	}

	public static void addGoodToDocument(String categName,
			String subcategName, Good good, Document document) {
		NodeList categNodes = document.getElementsByTagName(CATEGORY_ELEM);
		for (int ci = 0; ci < categNodes.getLength(); ci++) {
			Element categElem = (Element) categNodes.item(ci);
			String nameAttrVal = categElem.getAttribute(NAME_ATTR);
			if (categName.equals(nameAttrVal)) {
				NodeList subcategNodes = categElem
						.getElementsByTagName(SUBCATEGORY_ELEM);
				addGoodToAppropriateSubcateg(document, subcategNodes, subcategName,
						good);
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
