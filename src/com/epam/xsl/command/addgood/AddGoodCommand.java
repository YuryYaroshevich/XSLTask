package com.epam.xsl.command.addgood;

import static com.epam.xsl.command.util.FileURLContainer.ADD_GOOD_XSLT;
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
import com.epam.xsl.command.util.XMLWrapper;

public final class AddGoodCommand implements Command {
	// parameter name
	private static final String CATEGORY_NAME = "categoryName";
	private static final String SUBCATEGORY_NAME = "subcategoryName";

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			Templates addGood = TemplatesCache
					.getTemplates(getFileURL(ADD_GOOD_XSLT));
			Transformer transf = addGood.newTransformer();
			// sets parameters
			transf.setParameter(CATEGORY_NAME,
					request.getParameter(CATEGORY_NAME));
			transf.setParameter(SUBCATEGORY_NAME,
					request.getParameter(SUBCATEGORY_NAME));
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
