package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import com.epam.xsl.command.exception.CommandException;

public abstract class Command {
	protected static final TransformerFactory transformerFactory = TransformerFactory
			.newInstance();
	
	public abstract Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException;
}
