package com.epam.xsl.command.util;

import static com.epam.xsl.command.util.FileURLContainer.PRODUCTS_XML;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.xsl.product.Good;

public final class XMLWrapper {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(
			true);
	private static final Lock readLock = lock.readLock();
	private static final Lock writeLock = lock.writeLock();

	private static final TransformerFactory transfFactory = TransformerFactory
			.newInstance();

	private static final ProductsDocumentWrapper productsDocWrapper = new ProductsDocumentWrapper();

	private XMLWrapper() {
	}

	public static Document readFromXML() throws Exception {
		readLock.lock();
		try {
			return productsDocWrapper.getProductsDocument();
		} catch (Exception e) {
			throw e;
		} finally {
			readLock.unlock();
		}
	}

	public static Document writeToXML(String categName, String subcategName,
			Good good) throws Exception {
		writeLock.lock();
		try {
			// adding new good to Document object
			Document document = productsDocWrapper.getProductsDocument();
			ProductsDOMManipulator.addGoodToDocument(categName, subcategName,
					good, document);
			// writing to XML
			StreamResult outputTarget = new StreamResult(new File(
					getFileURL(PRODUCTS_XML)));
			Transformer transf = transfFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			transf.transform(source, outputTarget);
			return document;
		} catch (Exception e) {
			throw e;
		} finally {
			writeLock.unlock();
		}
	}
}
