package com.epam.xsl.command.getentity;

import static com.epam.xsl.command.util.FileURLContainer.SUBCATEGORIES_XSLT;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;
import com.epam.xsl.command.xmlwrapper.XMLWrapper;

public final class SubcategoriesCommand implements Command {
	// parameter name
	private static final String CATEGORY_NAME = "categoryName";

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			Templates subcategoriesTempl = TemplatesCache
					.getTemplates(getFileURL(SUBCATEGORIES_XSLT));
			Transformer transf = subcategoriesTempl.newTransformer();
			transf.setParameter(CATEGORY_NAME,
					request.getParameter(CATEGORY_NAME));
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
