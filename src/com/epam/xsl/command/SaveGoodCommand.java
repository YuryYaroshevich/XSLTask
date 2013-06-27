package com.epam.xsl.command;

import static com.epam.xsl.appconstant.AppConstant.*;
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
import com.epam.xsl.util.TemplatesCache;

public final class SaveGoodCommand implements Command {
	private static final String QUERY_START = getProperty(REDIRECT_QUERY_START);

	private static final String CATEGORY_NAME = "categoryName";
	private static final String SUBCATEGORY_NAME = "subcategoryName";
	private static final String PRODUCER = "producer";
	private static final String MODEL = "model";
	private static final String DATE_OF_ISSUE = "dateOfIssue";
	private static final String COLOR = "color";
	private static final String PRICE = "price";
	private static final String NOT_IN_STOCK = "notInStock";
	
	// if checkbox wasn't checked then notInStock sets to false
	private static final String FALSE = "false";
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		try {
			String categoryName = req.getParameter(CATEGORY_NAME);
			String subcategoryName = req.getParameter(SUBCATEGORY_NAME);
			Templates validationTempl = TemplatesCache
					.getTemplates(getProperty(VALIDATION_XSLT));
			Transformer transf = validationTempl.newTransformer();
			
			transf.setParameter(CATEGORY_NAME, categoryName);
			transf.setParameter(SUBCATEGORY_NAME, subcategoryName);
			transf.setParameter(PRODUCER, req.getParameter(PRODUCER));
			transf.setParameter(MODEL, req.getParameter(MODEL));
			transf.setParameter(DATE_OF_ISSUE, req.getParameter(DATE_OF_ISSUE));
			transf.setParameter(COLOR, req.getParameter(COLOR));
			transf.setParameter(PRICE, req.getParameter(PRICE));
			String notInStock = req.getParameter(NOT_IN_STOCK);
			if (notInStock == null) {
				notInStock = FALSE;
			}
			transf.setParameter(NOT_IN_STOCK, notInStock);
			
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
