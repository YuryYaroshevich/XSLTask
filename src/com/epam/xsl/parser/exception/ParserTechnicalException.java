package com.epam.xsl.parser.exception;

import com.epam.xsl.exception.TechnicalException;

public class ParserTechnicalException extends TechnicalException {
	private static final long serialVersionUID = 5073133169742245193L;

	public ParserTechnicalException(Exception e) {
		super(e);
	}
}
