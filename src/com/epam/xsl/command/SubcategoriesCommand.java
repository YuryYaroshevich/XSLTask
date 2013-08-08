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

public final class SubcategoriesCommand implements Command {
	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		Synchronizer.getReadLock().lock();
		try {
			Transformer transf = TemplatesCache
					.getCorrespondTransf(getProperty(SUBCATEGORIES_XSLT));
			transf.setParameter(CATEGORY_NAME,
					request.getParameter(CATEGORY_NAME));
			StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
			StreamResult output = new StreamResult(response.getWriter());
			transf.transform(xmlSource, output);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		} finally {
			Synchronizer.getReadLock().unlock();
		}
	}
}
