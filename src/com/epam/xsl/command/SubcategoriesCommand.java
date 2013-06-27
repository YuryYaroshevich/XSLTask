package com.epam.xsl.command;

import static com.epam.xsl.appconstant.AppConstant.PRODUCTS_XML;
import static com.epam.xsl.appconstant.AppConstant.SUBCATEGORIES_XSLT;
import static com.resource.PropertyGetter.getProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.util.TemplatesCache;

public final class SubcategoriesCommand implements Command {
	// parameter name
	private static final String CATEGORY_NAME = "categoryName";

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			Templates subcategoriesTempl = TemplatesCache
					.getTemplates(getProperty(SUBCATEGORIES_XSLT));
			Transformer transf = subcategoriesTempl.newTransformer();
			transf.setParameter(CATEGORY_NAME,
					request.getParameter(CATEGORY_NAME));
			StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
			StreamResult output = new StreamResult(response.getWriter());
			transf.transform(xmlSource, output);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
}
