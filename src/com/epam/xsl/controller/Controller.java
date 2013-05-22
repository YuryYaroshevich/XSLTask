package com.epam.xsl.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.factory.CommandCreator;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 7688907932488576018L;

	private static final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
			.newInstance();

	private static final TransformerFactory transformerFactory = TransformerFactory
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
			File xml = new File(
					"C:/workspace/XSLTask/WebContent/WEB-INF/xml/Products.xml");
			DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
			Document document = builder.parse(xml);
            // get appropriate transformer
			Command command = CommandCreator.createCommand(request);
			Transformer transformer = command.execute(request, response);
			// transformation
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(response.getWriter());
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
