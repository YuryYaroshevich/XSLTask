package com.epam.xsl.util;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static com.epam.xsl.constant.AppConstant.VALIDATOR;

public final class ProductsXmlIO {
	private ProductsXmlIO() {
	}

	public static void readXML(Transformer transf, Source xmlSource,
			Result output) throws TransformerException {
		Synchronizer.getReadLock().lock();
		try {
			transf.transform(xmlSource, output);
		} finally {
			Synchronizer.getReadLock().unlock();
		}
	}

	public static void writeToXML(String information, String xmlPath,
			long lastModified, Transformer transf) throws Exception {
		FileWriter fileWriter = null;
		Synchronizer.getWriteLock().lock();
		try {
			File xml = new File(xmlPath);
			if (lastModified != xml.lastModified()) {
				GoodValidator validator = (GoodValidator) transf
						.getParameter(VALIDATOR);
				validator.reset();
				information = transformAndGetResult(transf, xmlPath);
			}
			fileWriter = new FileWriter(xml);
			fileWriter.append(information);
		} finally {
			Synchronizer.getWriteLock().unlock();
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}

	public static String transformAndGetResult(Transformer transf,
			String xmlPath) throws Exception {
		StringWriter stringWriter = null;
		try {
			StreamSource xmlSource = new StreamSource(xmlPath);
			stringWriter = new StringWriter();
			StreamResult output = new StreamResult(stringWriter);
			readXML(transf, xmlSource, output);
			return stringWriter.toString();
		} finally {
			if (stringWriter != null) {
				stringWriter.close();
			}
		}
	}
}
