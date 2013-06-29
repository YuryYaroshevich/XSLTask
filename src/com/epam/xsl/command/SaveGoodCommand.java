package com.epam.xsl.command;

import static com.epam.xsl.appconstant.AppConstant.GOODS_XSLT;
import static com.epam.xsl.appconstant.AppConstant.PRODUCTS_XML;
import static com.epam.xsl.appconstant.AppConstant.REDIRECT_QUERY_START;
import static com.epam.xsl.appconstant.AppConstant.VALIDATION_XSLT;
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
import com.epam.xsl.util.Synchronizer;
import com.epam.xsl.util.TemplatesCache;

public final class SaveGoodCommand implements Command {
	// start of URL for redirecting
	private static final String QUERY_START = getProperty(REDIRECT_QUERY_START);

	// parameter names for taking values from request
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
	// I use this constant for determining result of validation
	private static final String HTML = "html";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		Synchronizer.writeLock().lock();
		try {
			Templates validationTempl = TemplatesCache
					.getTemplates(getProperty(VALIDATION_XSLT));
			Transformer transf = validationTempl.newTransformer();
			setParametersInTransf(transf, req);
			String resultingInfo = resultOfTransformation(transf);
			if (resultingInfo.substring(1, 5).equals(HTML)) {
				// Validation didn't passed; form with error messages.
				resp.getWriter().append(resultingInfo);
			} else {
				// Validation passed; list of goods with new good.
				writeToXML(resultingInfo);
				listOfGoodsToPage(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		} finally {
			Synchronizer.writeLock().unlock();
		}
	}

	private static void setParametersInTransf(Transformer transf,
			HttpServletRequest req) {
		transf.setParameter(CATEGORY_NAME, req.getParameter(CATEGORY_NAME));
		transf.setParameter(SUBCATEGORY_NAME,
				req.getParameter(SUBCATEGORY_NAME));
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
	}

	private static String resultOfTransformation(Transformer transf)
			throws Exception {
		StringWriter stringWriter = null;
		try {
			StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
			stringWriter = new StringWriter();
			StreamResult output = new StreamResult(stringWriter);
			transf.transform(xmlSource, output);
			return stringWriter.toString();
		} finally {
			stringWriter.close();
		}
	}

	private static void writeToXML(String information) throws Exception {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(getProperty(PRODUCTS_XML));
			fileWriter.append(information);
		} finally {
			fileWriter.close();
		}
	}

	private static void listOfGoodsToPage(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String categoryName = req.getParameter(CATEGORY_NAME);
		String subcategoryName = req.getParameter(SUBCATEGORY_NAME);
		resp.sendRedirect(buildRedirectQuery(categoryName, subcategoryName));
		Templates goodsTempl = TemplatesCache
				.getTemplates(getProperty(GOODS_XSLT));
		Transformer transf = goodsTempl.newTransformer();
		StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
		StreamResult toPage = new StreamResult(resp.getWriter());
		transf.transform(xmlSource, toPage);
	}

	private static String buildRedirectQuery(String categName,
			String subcategName) {
		StringBuilder query = new StringBuilder(QUERY_START);
		query.append(CATEGORY_NAME + "=" + categName + "&");
		query.append(SUBCATEGORY_NAME + "=" + subcategName);
		return query.toString();
	}
}
