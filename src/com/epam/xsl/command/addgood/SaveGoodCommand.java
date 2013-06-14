package com.epam.xsl.command.addgood;

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
import com.epam.xsl.command.util.TemplatesCache;
import com.epam.xsl.command.xmlwrapper.XMLWrapper;
import com.epam.xsl.product.Good;
import com.resource.PropertyGetter;

import static com.resource.PropertyGetter.*;
import static com.epam.xsl.appconstant.AppConstant.*;

public final class SaveGoodCommand implements Command {
	// parameter names
	private static final String CATEGORY_NAME = "categoryName";
	private static final String SUBCATEGORY_NAME = "subcategoryName";
	private static final String PRODUCER = "producer";
	private static final String MODEL = "model";
	private static final String DATE_OF_ISSUE = "dateOfIssue";
	private static final String COLOR = "color";
	private static final String PRICE = "price";
	private static final String NOT_IN_STOCK = "notInStock";

	private static final String QUERY_START = PropertyGetter
			.getProperty(REDIRECT_QUERY_START);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws CommandException {
		try {
			String categoryName = request.getParameter(CATEGORY_NAME);
			String subcategoryName = request.getParameter(SUBCATEGORY_NAME);
			Good good = buildGood(request);
			Document document = XMLWrapper.writeToXML(categoryName,
					subcategoryName, good);
			Templates goodsTempl = TemplatesCache
					.getTemplates(getProperty(GOODS_XSLT));
			Transformer transf = goodsTempl.newTransformer();
			transf.setParameter(CATEGORY_NAME, categoryName);
			transf.setParameter(SUBCATEGORY_NAME, subcategoryName);
			// response.sendRedirect("http://localhost:8081/XSLTask/controller?command=GOODS&categoryName="+categoryName
			// +"&subcategoryName="+subcategoryName);
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
			good.setPrice(Integer.valueOf(req.getParameter(PRICE)));
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
