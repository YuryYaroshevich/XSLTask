package com.epam.xsl.parser.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.xsl.product.Category;
import com.epam.xsl.product.Good;
import com.epam.xsl.product.Subcategory;

import static com.epam.xsl.parser.ProductsXMLElement.*;

public class ProductsDOMHandler {
	private static final String NAME_ATTR = "name";

	public List<Category> getCategories(Element root) {
		List<Category> categories = new ArrayList<Category>();
		NodeList categoryNodes = root.getElementsByTagName(CATEGORY_ELEM);
		for (int i = 0; i < categoryNodes.getLength(); i++) {
			Element categoryElem = (Element) categoryNodes.item(i);
			categories.add(buildCategory(categoryElem));
		}
		return categories;
	}

	private static Category buildCategory(Element categoryElem) {
		Attr nameAttr = (Attr) categoryElem.getAttributes().getNamedItem(
				NAME_ATTR);
		Category category = new Category(nameAttr.getValue());
		NodeList subcategoryNodes = categoryElem
				.getElementsByTagName(SUBCATEGORY_ELEM);
		for (int j = 0; j < subcategoryNodes.getLength(); j++) {
			Element subcategoryElem = (Element) subcategoryNodes.item(j);
			category.addSubcategory(buildSubcategory(subcategoryElem));
		}
		return category;
	}

	private static Subcategory buildSubcategory(Element subcategoryElem) {
		Attr nameAttr = (Attr) subcategoryElem.getAttributes().getNamedItem(
				NAME_ATTR);
		Subcategory subcategory = new Subcategory(nameAttr.getValue());
		NodeList goodNodes = subcategoryElem.getElementsByTagName(GOOD_ELEM);
		for (int k = 0; k < goodNodes.getLength(); k++) {
			Element goodElem = (Element) goodNodes.item(k);
			subcategory.addGood(buildGood(goodElem));
		}
		return subcategory;
	}

	private static Good buildGood(Element goodElem) {
		Good good = new Good();
		good.setProducer(getChildValue(goodElem, PRODUCER_ELEM));
		good.setModel(getChildValue(goodElem, MODEL_ELEM));
		good.setDateOfIssue(getChildValue(goodElem, DATE_OF_ISSUE_ELEM));
		good.setColor(getChildValue(goodElem, COLOR_ELEM));
		setGoodShopingState(good, goodElem);
		return good;
	}

	private static void setGoodShopingState(Good good, Element goodElem) {
		Element priceElem = getChildByName(goodElem, PRICE_ELEM);
		if (priceElem != null) {
			Node price = priceElem.getFirstChild();
			good.setPrice(Integer.valueOf(price.getNodeValue()));
		} else {
			good.markAsNotInStock();
		}
	}

	private static Element getChildByName(Element parent, String childName) {
		NodeList nodeList = parent.getElementsByTagName(childName);
		return (Element) nodeList.item(0);
	}

	private static String getChildValue(Element parent, String childName) {
		Element child = getChildByName(parent, childName);
		Node text = child.getFirstChild();
		return text.getNodeValue();
	}
}
