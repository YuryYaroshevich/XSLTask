package com.epam.xsl.exception;

public abstract class XSLTaskException extends Exception {
	private static final long serialVersionUID = -9176692414785303498L;
	
	protected Throwable hidden;

	public XSLTaskException(Throwable e) {
		super(e);
		hidden = e;
	}
	
	public String toString() {
		return super.toString();
	}
}
