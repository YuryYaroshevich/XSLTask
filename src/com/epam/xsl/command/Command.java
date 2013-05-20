package com.epam.xsl.command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public File execute(HttpServletRequest request,
			HttpServletResponse response);
}
