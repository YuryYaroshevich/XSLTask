package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.xsl.command.exception.CommandException;

public interface Command {
	void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException;
}
