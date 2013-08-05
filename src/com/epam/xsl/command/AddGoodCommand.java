package com.epam.xsl.command;

import static com.epam.xsl.appconstant.AppConstant.ADD_GOOD_XSLT;
import static com.epam.xsl.appconstant.AppConstant.PRODUCTS_XML;
import static com.resource.PropertyGetter.getProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.util.Synchronizer;
import com.epam.xsl.util.TemplatesCache;

public final class AddGoodCommand implements Command {
	// parameter name
	private static final String CATEGORY_NAME = "categoryName";
	private static final String SUBCATEGORY_NAME = "subcategoryName";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		Synchronizer.getReadLock().lock();
		try {
			Templates addGood = TemplatesCache
					.getTemplates(getProperty(ADD_GOOD_XSLT));
			Transformer transf = addGood.newTransformer();
			// sets parameters
			transf.setParameter(CATEGORY_NAME,
					req.getParameter(CATEGORY_NAME));
			transf.setParameter(SUBCATEGORY_NAME,
					req.getParameter(SUBCATEGORY_NAME));
			// prepare transformation
			StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
			Result output = new StreamResult(resp.getWriter());
			transf.transform(xmlSource, output);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		} finally {
			Synchronizer.getReadLock().unlock();
		}
	}
}
