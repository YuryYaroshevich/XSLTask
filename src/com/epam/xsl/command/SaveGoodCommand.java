package com.epam.xsl.command;

import static com.epam.xsl.appconstant.AppConstant.CATEGORY_NAME;
import static com.epam.xsl.appconstant.AppConstant.COLOR;
import static com.epam.xsl.appconstant.AppConstant.DATE_OF_ISSUE;
import static com.epam.xsl.appconstant.AppConstant.MODEL;
import static com.epam.xsl.appconstant.AppConstant.NOT_IN_STOCK;
import static com.epam.xsl.appconstant.AppConstant.PRICE;
import static com.epam.xsl.appconstant.AppConstant.PRODUCER;
import static com.epam.xsl.appconstant.AppConstant.REDIRECT_QUERY_START;
import static com.epam.xsl.appconstant.AppConstant.SAVE_GOOD_XSLT;
import static com.epam.xsl.appconstant.AppConstant.SUBCATEGORY_NAME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;
import com.resource.PropertyGetter;

public final class SaveGoodCommand implements Command {
	private static final String QUERY_START = PropertyGetter
			.getProperty(REDIRECT_QUERY_START);

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		try {
			String categoryName = req.getParameter(CATEGORY_NAME);
			String subcategoryName = req.getParameter(SUBCATEGORY_NAME);
			Templates validationTempl = TemplatesCache
					.getTemplates(SAVE_GOOD_XSLT);
			Transformer transf = validationTempl.newTransformer();

			transf.setParameter(CATEGORY_NAME, categoryName);
			transf.setParameter(SUBCATEGORY_NAME, subcategoryName);
			transf.setParameter(PRODUCER, req.getParameter(PRODUCER));
			transf.setParameter(MODEL, req.getParameter(MODEL));
			transf.setParameter(DATE_OF_ISSUE, req.getParameter(DATE_OF_ISSUE));
			transf.setParameter(COLOR, req.getParameter(COLOR));
			transf.setParameter(NOT_IN_STOCK, req.getParameter(PRICE));

			resp.sendRedirect(buildRedirectQuery(categoryName, subcategoryName));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

	private static String buildRedirectQuery(String categName,
			String subcategName) {
		StringBuilder query = new StringBuilder(QUERY_START);
		query.append(CATEGORY_NAME + "=" + categName + "&");
		query.append(SUBCATEGORY_NAME + "=" + subcategName);
		return query.toString();
	}
}
