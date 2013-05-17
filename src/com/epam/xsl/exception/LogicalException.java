package com.epam.xsl.exception;

public abstract class LogicalException extends XSLTaskException {
	private static final long serialVersionUID = 1L;

	public LogicalException(Exception e) {
		super(e);
	}
}
