package com.epam.xsl.command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.xsl.command.util.XSLTContainer;

public class NoCommand implements Command {
	@Override
	public File execute(HttpServletRequest request, HttpServletResponse response) {
		return XSLTContainer.CATEGORIES_XSLT;
	}
}
