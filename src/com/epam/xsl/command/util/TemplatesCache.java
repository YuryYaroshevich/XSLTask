package com.epam.xsl.command.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public final class TemplatesCache {
	private static Map<String, TemplatesWrapper> cache = new HashMap<String, TemplatesWrapper>();

	private static final TransformerFactory transformerFactory = TransformerFactory
			.newInstance();

	private static final Lock lock = new ReentrantLock();

	private TemplatesCache() {
	}

	public static Templates getTemplates(String xsltPath)
			throws TransformerConfigurationException {
		File xsltFile = new File(xsltPath);
		TemplatesWrapper templ = cache.get(xsltPath);
		long lastModified = xsltFile.lastModified();

		if (templ != null) {
			// check if XSLT file was changed
			if (lastModified > templ.lastModified) {
				templ = null;
			}
		}

		if (templ == null) {
			templ = newEntryInCache(lastModified, xsltPath);
		}
		return templ.templates;
	}

	private static TemplatesWrapper newEntryInCache(long lastModified, String key)
			throws TransformerConfigurationException {
		lock.lock();
		try {
			TemplatesWrapper templWrapper = cache.get(key);
			// check if other thread updated entry in a cache
			if (lastModified == templWrapper.lastModified) {
				return templWrapper;
			}
			// if didn't, put new entry in a cache
			Templates templates = transformerFactory
					.newTemplates(new StreamSource(key));
			templWrapper = new TemplatesWrapper(templates, lastModified);
			cache.put(key, templWrapper);
			return templWrapper;
		} finally {
			lock.unlock();
		}
	}

	private static class TemplatesWrapper {
		Templates templates;
		long lastModified;

		TemplatesWrapper(Templates templates, long lastModified) {
			this.templates = templates;
			this.lastModified = lastModified;
		}
	}
}
