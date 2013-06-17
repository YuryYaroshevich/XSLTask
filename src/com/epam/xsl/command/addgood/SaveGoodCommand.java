package com.epam.xsl.command.addgood;

import static com.epam.xsl.appconstant.AppConstant.*;
import static com.resource.PropertyGetter.getProperty;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.GoodValidator;
import com.epam.xsl.command.util.TemplatesCache;
import com.epam.xsl.command.xmlwrapper.XMLWrapper;
import com.epam.xsl.product.Good;
import com.resource.PropertyGetter;

public final class SaveGoodCommand implements Command {
	private static final String QUERY_START = PropertyGetter
			.getProperty(REDIRECT_QUERY_START);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException {
		try {
			String categoryName = request.getParameter(CATEGORY_NAME);
			String subcategoryName = request.getParameter(SUBCATEGORY_NAME);

			GoodValidator validator = new GoodValidator();
			Document document = null;
			Transformer transf = null;
			Good good = buildGood(request);
			if (validator.isValid(request)) {
				Templates goodsTempl = TemplatesCache
						.getTemplates(getProperty(GOODS_XSLT));
				transf = goodsTempl.newTransformer();
				document = XMLWrapper.writeToXML(categoryName, subcategoryName,
						good);
			} else {
				Templates addGoodTempl = TemplatesCache.getTemplates(ADD_GOOD_XSLT);
				transf = addGoodTempl.newTransformer();
				transf.setParameter("errors", validator.getErrors());
				transf.setParameter("good", good);
			}

			transf.setParameter(CATEGORY_NAME, categoryName);
			transf.setParameter(SUBCATEGORY_NAME, subcategoryName);

			response.sendRedirect(buildRedirectQuery(categoryName,
					subcategoryName));
			applyTransformation(transf, document, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

	private static Good buildGood(HttpServletRequest req) {
		Good good = new Good();
		good.setProducer(req.getParameter(PRODUCER));
		good.setModel(req.getParameter(MODEL));
		good.setDateOfIssue(req.getParameter(DATE_OF_ISSUE));
		good.setColor(req.getParameter(COLOR));
		boolean notInStock = Boolean.valueOf(req.getParameter(NOT_IN_STOCK));
		if (notInStock) {
			good.markAsNotInStock();
		} else {
			good.setPrice(req.getParameter(PRICE));
		}
		return good;
	}

	private static String buildRedirectQuery(String categName,
			String subcategName) {
		StringBuilder query = new StringBuilder(QUERY_START);
		query.append(CATEGORY_NAME + "=" + categName + "&");
		query.append(SUBCATEGORY_NAME + "=" + subcategName);
		return query.toString();
	}

	private static void applyTransformation(Transformer transf,
			Document document, HttpServletResponse resp)
			throws TransformerException, IOException {
		DOMSource source = new DOMSource(document);
		Result outputTarget = new StreamResult(resp.getWriter());
		transf.transform(source, outputTarget);
	}
}
