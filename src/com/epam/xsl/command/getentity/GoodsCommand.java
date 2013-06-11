package com.epam.xsl.command.getentity;

import static com.epam.xsl.command.util.FileURLContainer.GOODS_XSLT;
import static com.epam.xsl.command.util.FileURLContainer.getFileURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;

public final class GoodsCommand implements Command {
	// parameter names
	private static final String SUBCATEGORY_NAME = "subcategoryName";
	private static final String CATEGORY_NAME = "categoryName";

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			Templates goods = TemplatesCache
					.getTemplates(getFileURL(GOODS_XSLT));
			Transformer transf = goods.newTransformer();
			// sets parameters
			transf.setParameter(SUBCATEGORY_NAME,
					request.getParameter(SUBCATEGORY_NAME));
			transf.setParameter(CATEGORY_NAME,
					request.getParameter(CATEGORY_NAME));
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

}
