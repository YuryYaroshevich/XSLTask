package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.XSLTContainer;

public class SubcategoriesCommand extends Command {
	private static final String PARAM_CATEGORY = "category";

	@Override
	public Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			StreamSource styleSource = new StreamSource(
					XSLTContainer.SUBCATEGORIES_XSLT);
			Transformer transf = transformerFactory.newTransformer(styleSource);
			transf.setParameter(PARAM_CATEGORY,
					request.getParameter(PARAM_CATEGORY));
			System.out.println(request.getParameter(PARAM_CATEGORY));
			return transf;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
}
