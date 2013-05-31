package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import com.epam.xsl.command.exception.CommandException;
import static com.epam.xsl.command.util.FileURLContainer.*;
import com.epam.xsl.command.util.TemplatesCache;

public class NoCommand extends Command {
	@Override
	public Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
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
