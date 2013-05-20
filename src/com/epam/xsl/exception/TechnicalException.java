package com.epam.xsl.exception;

public abstract class TechnicalException extends XSLTaskException {
	private static final long serialVersionUID = 8364410416562733013L;

	public TechnicalException(Throwable e) {
		super(e);
	}
}
