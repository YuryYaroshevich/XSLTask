package com.epam.xsl.command.util;

import static com.epam.xsl.appconstant.AppConstant.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class GoodValidator {
	private Map<String, String> errors;

	public GoodValidator() {
		errors = new HashMap<String, String>();
	}

	public boolean isValid(HttpServletRequest req) {
		boolean isValid = true;
		if ("".equals(req.getParameter(PRODUCER))) {
			errors.put(PRODUCER, "producer empty");
			isValid = false;
		} else if ("".equals(req.getParameter(MODEL))) {
			errors.put(MODEL, "model empty");
			isValid = false;
		} else if ("".equals(req.getParameter(DATE_OF_ISSUE))) {
			errors.put(DATE_OF_ISSUE, "date empty");
			isValid = false;
		} else if ("".equals(req.getParameter(COLOR))) {
			errors.put(COLOR, "color empty");
			isValid = false;
		} else if ("".equals(req.getParameter(PRICE))) {
			if (!Boolean.valueOf(req.getParameter(NOT_IN_STOCK))) {
				errors.put(NOT_IN_STOCK, "set price or mark as not in stock");
				isValid = false;
			}
		}
		return isValid;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	public String getErrorMessage(String key) {
		return errors.get(key);
	}
}
