package com.epam.xsl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.factory.CommandCreator;

public final class ProductsXSLServlet extends HttpServlet {
	private static final long serialVersionUID = 7688907932488576018L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private static void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Command command = CommandCreator.createCommand(request);
			command.execute(request, response);			
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
}

