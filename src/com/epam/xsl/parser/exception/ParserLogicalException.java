package com.epam.xsl.parser.exception;

import com.epam.xsl.exception.LogicalException;

public class ParserLogicalException extends LogicalException {
	private static final long serialVersionUID = -6861216893130830174L;

	public ParserLogicalException(Exception e) {
		super(e);
	}
}
