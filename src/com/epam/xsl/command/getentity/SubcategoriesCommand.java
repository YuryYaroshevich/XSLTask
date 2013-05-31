package com.epam.xsl.command.getentity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import static com.epam.xsl.command.util.FileURLContainer.*;
import com.epam.xsl.command.util.TemplatesCache;

public class SubcategoriesCommand extends Command {
	private static final String PARAM_CATEGORY_NAME = "categoryName";

	@Override
	public Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			Templates subcategories = TemplatesCache
					.getTemplates(getFileURL(SUBCATEGORIES_XSLT));
			Transformer transf = subcategories.newTransformer();
			transf.setParameter(PARAM_CATEGORY_NAME,
					request.getParameter(PARAM_CATEGORY_NAME));
			return transf;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
}
