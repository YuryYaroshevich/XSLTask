package com.epam.xsl.command.getentity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesContainer;

public class GoodsCommand extends Command {
	private static final String PARAM_SUBCATEGORY_NAME = "subcategoryName";

	@Override
	public Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			TemplatesContainer container = TemplatesContainer.getInstance();
			Transformer transf = container.GOODS_XSLT.newTransformer();
			transf.setParameter(PARAM_SUBCATEGORY_NAME,
					request.getParameter(PARAM_SUBCATEGORY_NAME));
			return transf;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

}
