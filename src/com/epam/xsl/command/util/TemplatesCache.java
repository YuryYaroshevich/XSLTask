package com.epam.xsl.command.util;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class TemplatesCache {
	private static Map<String, MapEntry> cache = new ConcurrentHashMap<String, MapEntry>();

	private static final TransformerFactory transformerFactory = TransformerFactory
			.newInstance();

	private TemplatesCache() {
	}

	public static Templates getTemplates(String xsltPath)
			throws TransformerConfigurationException {
		File xsltFile = new File(xsltPath);
		long lastModified = xsltFile.lastModified();
		MapEntry entry = cache.get(xsltPath);

		if (entry != null) {
			if (lastModified > entry.lastModified) {
				entry = null;
			}
		}

		if (entry == null) {
			entry = createEntry(lastModified, xsltPath);
		}

		return entry.templates;
	}

	private static MapEntry createEntry(long lastModified,
			String entryKey) throws TransformerConfigurationException {
		Templates templates = transformerFactory.newTemplates(new StreamSource(
				entryKey));
		MapEntry entry = new MapEntry(templates, lastModified);
		cache.put(entryKey, entry);
		return entry;
	}

	private static class MapEntry {
		Templates templates;
		long lastModified;

		MapEntry(Templates templates, long lastModified) {
			this.templates = templates;
			this.lastModified = lastModified;
		}
	}
}
