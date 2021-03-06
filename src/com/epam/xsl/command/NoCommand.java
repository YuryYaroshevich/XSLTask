package com.epam.xsl.command;

import static com.epam.xsl.command.util.FileURLContainer.CATEGORIES_XSLT;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;

public final class NoCommand implements Command {
	@Override
	public Transformer execute(HttpServletRequest request)
			throws CommandException {
		try {
			Templates categories = TemplatesCache
					.getTemplates(getFileURL(CATEGORIES_XSLT));
			return categories.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
}
