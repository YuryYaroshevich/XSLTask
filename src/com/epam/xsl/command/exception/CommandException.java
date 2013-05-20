package com.epam.xsl.command.exception;

import com.epam.xsl.exception.TechnicalException;

public class CommandException extends TechnicalException {
	public CommandException(Throwable e) {
		super(e);
	}	
}
