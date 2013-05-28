package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesContainer;

public class NoCommand extends Command {
	@Override
	public Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			TemplatesContainer container = TemplatesContainer.getInstance();
			return container.CATEGORIES_XSLT.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
}
