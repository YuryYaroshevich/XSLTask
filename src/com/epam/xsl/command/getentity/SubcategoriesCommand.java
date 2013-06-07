package com.epam.xsl.command.getentity;

import static com.epam.xsl.command.util.FileURLContainer.SUBCATEGORIES_XSLT;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;

public final class SubcategoriesCommand implements Command {
	// parameter name
	private static final String CATEGORY_NAME = "categoryName";

	@Override
	public Transformer execute(HttpServletRequest request)
			throws CommandException {
		try {
			Templates subcategories = TemplatesCache
					.getTemplates(getFileURL(SUBCATEGORIES_XSLT));
			Transformer transf = subcategories.newTransformer();
			transf.setParameter(CATEGORY_NAME,
					request.getParameter(CATEGORY_NAME));
			return transf;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
}
