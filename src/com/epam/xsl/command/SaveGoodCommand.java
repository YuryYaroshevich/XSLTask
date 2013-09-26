package com.epam.xsl.command;

import static com.epam.xsl.constant.AppConstant.CATEGORY_NAME;
import static com.epam.xsl.constant.AppConstant.GOODS_XSLT;
import static com.epam.xsl.constant.AppConstant.PRODUCTS_XML;
import static com.epam.xsl.constant.AppConstant.REDIRECT_QUERY_START;
import static com.epam.xsl.constant.AppConstant.SUBCATEGORY_NAME;
import static com.epam.xsl.constant.AppConstant.VALIDATION_XSLT;
import static com.epam.xsl.constant.AppConstant.VALIDATOR;
import static com.epam.xsl.resource.PropertyGetter.getProperty;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.util.GoodValidator;
import com.epam.xsl.util.ParameterTransporter;
import com.epam.xsl.util.ProductsXmlIO;
import com.epam.xsl.util.Synchronizer;
import com.epam.xsl.util.TemplatesCache;

final class SaveGoodCommand implements Command {
	// start of URL for redirecting
	private static final String QUERY_START = getProperty(REDIRECT_QUERY_START);

	// parameter names for taking values from request and setting values in
	// transformer
	private static final String NOT_IN_STOCK = "notInStock";

	// if checkbox wasn't checked then notInStock sets to false
	private static final String FALSE = "false";

	private static final Command command = new SaveGoodCommand();

	private SaveGoodCommand() {
	}

	public static Command getInstance() {
		return command;
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		try {
			Transformer transf = TemplatesCache
					.getCorrespondTransf(getProperty(VALIDATION_XSLT));
			GoodValidator validator = setParametersInTransf(transf, req);

			// read products.xml and add new good to buffer by applying
			// transformation
			Synchronizer.getReadLock().lock();
			File xml;
			long lastModified;
			String resultingInfo;
			try {
				xml = new File(getProperty(PRODUCTS_XML));
				lastModified = xml.lastModified();
				resultingInfo = ProductsXmlIO.transformAndGetResult(transf,
						getProperty(PRODUCTS_XML));
			} finally {
				Synchronizer.getReadLock().unlock();
			}

			// check the result of transformation
			if (validator.isGoodValid()) {
				// Validation passed; list of goods with new good
				ProductsXmlIO.writeToXML(resultingInfo,
						getProperty(PRODUCTS_XML), lastModified, transf);
				viewListOfGoods(req, resp);
			} else {
				// Validation didn't passed; form with error messages
				resp.getWriter().append(resultingInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

	private static GoodValidator setParametersInTransf(Transformer transf,
			HttpServletRequest req) {
		GoodValidator validator = new GoodValidator();
		transf.setParameter(VALIDATOR, validator);
		ParameterTransporter.transportFromRequestToTransformer(req, transf);
		String notInStock = req.getParameter(NOT_IN_STOCK);
		if (notInStock == null) {
			notInStock = FALSE;
		}
		transf.setParameter(NOT_IN_STOCK, notInStock);

		return validator;
	}

	private static void viewListOfGoods(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String categoryName = req.getParameter(CATEGORY_NAME);
		String subcategoryName = req.getParameter(SUBCATEGORY_NAME);
		resp.sendRedirect(buildRedirectQuery(categoryName, subcategoryName));
		Transformer transf = TemplatesCache
				.getCorrespondTransf(getProperty(GOODS_XSLT));
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
