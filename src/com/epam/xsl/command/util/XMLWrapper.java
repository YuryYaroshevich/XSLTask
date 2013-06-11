package com.epam.xsl.command.util;

import static com.epam.xsl.command.util.FileURLContainer.PRODUCTS_XML;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

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
			return ProductsDOMManipulator.addGoodToDocument(categName,
					subcategName, good, document);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			writeLock.unlock();
		}
	}
}
