package com.epam.xsl.command.getentity;

import static com.epam.xsl.appconstant.AppConstant.*;
import static com.resource.PropertyGetter.getProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;
import com.epam.xsl.command.util.TemplatesCache;
import com.epam.xsl.command.xmlwrapper.XMLWrapper;

public final class GoodsCommand implements Command {
	// parameter names
	private static final String SUBCATEGORY_NAME = "subcategoryName";
	private static final String CATEGORY_NAME = "categoryName";

	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		try {
			Templates goodsTempl = TemplatesCache
					.getTemplates(getProperty(GOODS_XSLT));
			Transformer transf = goodsTempl.newTransformer();
			// sets parameters
			transf.setParameter(SUBCATEGORY_NAME,
					request.getParameter(SUBCATEGORY_NAME));
			transf.setParameter(CATEGORY_NAME,
					request.getParameter(CATEGORY_NAME));
			applyTransformation(transf, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

	private static void applyTransformation(Transformer transf,
			HttpServletResponse response) throws Exception {
		Document document = XMLWrapper.readFromXML();
		DOMSource source = new DOMSource(document);
		Result outputTarget = new StreamResult(response.getWriter());
		transf.transform(source, outputTarget);
	}	
}
