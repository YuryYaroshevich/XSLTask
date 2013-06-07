package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Transformer;

import com.epam.xsl.command.exception.CommandException;

public interface Command {
	Transformer execute(HttpServletRequest request) throws CommandException;
}
