package com.epam.xsl.command;

import static com.epam.xsl.command.util.FileURLContainer.CATEGORIES_XSLT;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;
import com.epam.xsl.command.xmlwrapper.XMLWrapper;

public final class NoCommand implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException {
		try {
			Templates categoriesTempl = TemplatesCache
					.getTemplates(getFileURL(CATEGORIES_XSLT));
			Transformer transf = categoriesTempl.newTransformer();
			applyTransformation(transf, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

	private static void applyTransformation(Transformer transf,
			HttpServletResponse response) throws Exception {
		Document document = XMLWrapper.readFromXML();
		DOMSource source = new DOMSource(document);
		Result outputTarget = new StreamResult(response.getWriter());
		transf.transform(source, outputTarget);
	}
}
