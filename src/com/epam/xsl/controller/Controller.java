package com.epam.xsl.controller;

import static com.epam.xsl.command.util.FileURLContainer.PRODUCTS_XML;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.factory.CommandCreator;

public final class Controller extends HttpServlet {
	private static final long serialVersionUID = 7688907932488576018L;

	private static final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
			.newInstance();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// build document
			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
			Document document = builder.parse(getFileURL(PRODUCTS_XML)); 
			// get appropriate transformer
			Command command = CommandCreator.createCommand(request);
			command.execute(request, response);
			// transformation
			DOMSource source = new DOMSource(document);
			Result result = new StreamResult(response.getWriter());
			//transf.transform(source, result);
		} catch (CommandException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
