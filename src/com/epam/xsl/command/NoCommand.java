package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.FileURLContainer;

public class NoCommand extends Command {
	@Override
	public Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			Source styleSource = new StreamSource(
					FileURLContainer.CATEGORIES_XSLT);
			return transformerFactory.newTransformer(styleSource);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
}
