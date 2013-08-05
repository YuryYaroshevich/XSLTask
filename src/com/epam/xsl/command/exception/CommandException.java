package com.epam.xsl.command.exception;

import com.epam.xsl.exception.TechnicalException;

public class CommandException extends TechnicalException {
	private static final long serialVersionUID = -1686841365914586263L;

	public CommandException(Throwable e) {
		super(e);
	}	
}
