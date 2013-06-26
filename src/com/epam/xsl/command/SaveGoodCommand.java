package com.epam.xsl.command;

import static com.epam.xsl.appconstant.AppConstant.CATEGORY_NAME;
import static com.epam.xsl.appconstant.AppConstant.COLOR;
import static com.epam.xsl.appconstant.AppConstant.DATE_OF_ISSUE;
import static com.epam.xsl.appconstant.AppConstant.GOODS_XSLT;
import static com.epam.xsl.appconstant.AppConstant.MODEL;
import static com.epam.xsl.appconstant.AppConstant.PRICE;
import static com.epam.xsl.appconstant.AppConstant.PRODUCER;
import static com.epam.xsl.appconstant.AppConstant.PRODUCTS_XML;
import static com.epam.xsl.appconstant.AppConstant.REDIRECT_QUERY_START;
import static com.epam.xsl.appconstant.AppConstant.SAVE_GOOD_XSLT;
import static com.epam.xsl.appconstant.AppConstant.SUBCATEGORY_NAME;
import static com.resource.PropertyGetter.getProperty;

import java.io.FileWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;

public final class SaveGoodCommand implements Command {
	private static final String QUERY_START = getProperty(REDIRECT_QUERY_START);

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		try {
			String categoryName = req.getParameter(CATEGORY_NAME);
			String subcategoryName = req.getParameter(SUBCATEGORY_NAME);
			Templates validationTempl = TemplatesCache
					.getTemplates(getProperty(SAVE_GOOD_XSLT));
			Transformer transf = validationTempl.newTransformer();

			transf.setParameter(CATEGORY_NAME, categoryName);
			transf.setParameter(SUBCATEGORY_NAME, subcategoryName);
			transf.setParameter(PRODUCER, req.getParameter(PRODUCER));
			transf.setParameter(MODEL, req.getParameter(MODEL));
			transf.setParameter(DATE_OF_ISSUE, req.getParameter(DATE_OF_ISSUE));
			transf.setParameter(COLOR, req.getParameter(COLOR));
			transf.setParameter(PRICE, req.getParameter(PRICE));
			
			StreamSource XMLSource = new StreamSource(getProperty(PRODUCTS_XML));
			StringWriter stringWriter = new StringWriter();
			transf.transform(XMLSource,  new StreamResult(stringWriter));
			FileWriter fileWriter = new FileWriter(getProperty(PRODUCTS_XML));
			fileWriter.append(stringWriter.getBuffer());
			fileWriter.close();
			stringWriter.close();
			
			resp.sendRedirect(buildRedirectQuery(categoryName, subcategoryName));
			Templates goodsTempl = TemplatesCache.getTemplates(getProperty(GOODS_XSLT));
			transf = goodsTempl.newTransformer();
			StreamResult toPage = new StreamResult(resp.getWriter());
			transf.transform(XMLSource, toPage);
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
