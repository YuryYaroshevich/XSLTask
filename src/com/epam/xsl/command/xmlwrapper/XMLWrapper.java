package com.epam.xsl.command.xmlwrapper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;

import com.epam.xsl.product.Good;

public final class XMLWrapper {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(
			true);
	private static final Lock readLock = lock.readLock();
	private static final Lock writeLock = lock.writeLock();

	private static final TransformerFactory transfFactory = TransformerFactory
			.newInstance();

	private XMLWrapper() {
	}

	public static Document readFromXML() throws Exception {
		readLock.lock();
		try {
			return null;
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

			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			writeLock.unlock();
		}
	}
}
