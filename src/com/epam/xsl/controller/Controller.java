package com.epam.xsl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.factory.CommandCreator;

public final class Controller extends HttpServlet {
	private static final long serialVersionUID = 7688907932488576018L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private static final String REQUEST_AGE = "requestAge";
	private static final String OLD = "old";

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if (isRequestNew(request)) {
				Command command = CommandCreator.createCommand(request);
				command.execute(request, response);
			}			
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isRequestNew(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		int hash = req.hashCode();
		Integer prevHash = (Integer)session.getAttribute("prevHash");
		System.out.println("hash from req:"+hash);
		System.out.println(prevHash);
		if (prevHash == null) {
			session.setAttribute("prevHash", hash);
			return true;
		} else if (prevHash == hash) {
			return false;
		} else if (prevHash != hash) {
			session.setAttribute("prevHash", hash);
			return true;
		}
		return false;
	}
}
