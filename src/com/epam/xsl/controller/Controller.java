package com.epam.xsl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.factory.CommandCreator;
import static com.epam.xsl.command.util.FileURLContainer.*;

public class Controller extends HttpServlet {
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
			Transformer transformer = command.execute(request, response);
			// transformation
			DOMSource source = new DOMSource(document);
			Result result = new StreamResult(response.getWriter());
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
}
