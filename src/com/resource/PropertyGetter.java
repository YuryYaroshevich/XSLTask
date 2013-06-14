package com.resource;

import java.util.ResourceBundle;

public final class PropertyGetter {
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("com.resource.resource");
	


	private PropertyGetter() {
	}

	public static String getProperty(String key) {
		return bundle.getString(key);
	}
}
