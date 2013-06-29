package com.epam.xsl.command;

import static com.epam.xsl.appconstant.AppConstant.GOODS_XSLT;
import static com.epam.xsl.appconstant.AppConstant.PRODUCTS_XML;
import static com.resource.PropertyGetter.getProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.util.TemplatesCache;
import com.epam.xsl.util.Synchronizer;

public final class GoodsCommand implements Command {
	// parameter names
	private static final String SUBCATEGORY_NAME = "subcategoryName";
	private static final String CATEGORY_NAME = "categoryName";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		Synchronizer.readLock().lock();
		try {
			Templates goodsTempl = TemplatesCache
					.getTemplates(getProperty(GOODS_XSLT));
			Transformer transf = goodsTempl.newTransformer();
			// sets parameters
			transf.setParameter(SUBCATEGORY_NAME,
					req.getParameter(SUBCATEGORY_NAME));
			transf.setParameter(CATEGORY_NAME, req.getParameter(CATEGORY_NAME));
			// prepare transformation
			StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
			StreamResult toPage = new StreamResult(resp.getWriter());
			transf.transform(xmlSource, toPage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		} finally {
			Synchronizer.readLock().unlock();
		}
	}
}
