package com.epam.xsl.command;

import static com.epam.xsl.constant.AppConstant.*;
import static com.epam.xsl.resource.PropertyGetter.getProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.util.Synchronizer;
import com.epam.xsl.util.TemplatesCache;

public final class CancelCommand implements Command {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		Synchronizer.getReadLock().lock();
		try {
			Transformer transf = TemplatesCache
					.getCorrespondTransf(getProperty(GOODS_XSLT));
			// sets parameters
			transf.setParameter(CATEGORY_NAME, req.getParameter(CATEGORY_NAME));
			transf.setParameter(SUBCATEGORY_NAME,
					req.getParameter(SUBCATEGORY_NAME));
			// prepare transformation
			StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
			StreamResult toPage = new StreamResult(resp.getWriter());
			transf.transform(xmlSource, toPage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		} finally {
			Synchronizer.getReadLock().unlock();
		}
	}
}
