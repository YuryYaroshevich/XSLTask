package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}
}
