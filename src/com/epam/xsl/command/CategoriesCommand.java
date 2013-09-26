package com.epam.xsl.command;

import static com.epam.xsl.constant.AppConstant.CATEGORIES_XSLT;
import static com.epam.xsl.constant.AppConstant.PRODUCTS_XML;
import static com.epam.xsl.resource.PropertyGetter.getProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.util.ProductsXmlIO;
import com.epam.xsl.util.TemplatesCache;

final class CategoriesCommand implements Command {
	private static final Command command = new CategoriesCommand();
	
	private CategoriesCommand() {
	}
	
	public static Command getInstance() {
		return command;
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws CommandException {
		try {
			Transformer transf = TemplatesCache
					.getCorrespondTransf(getProperty(CATEGORIES_XSLT));
			StreamSource xmlSource = new StreamSource(getProperty(PRODUCTS_XML));
			StreamResult toPage = new StreamResult(resp.getWriter());
			ProductsXmlIO.readXML(transf, xmlSource, toPage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		} 
	}
}
