package com.epam.xsl.parser;

import java.util.List;

import com.epam.xsl.parser.exception.ParserLogicalException;
import com.epam.xsl.parser.exception.ParserTechnicalException;
import com.epam.xsl.product.Category;

public interface ProductsXMLParser {
	List<Category> parse(String xml) throws ParserLogicalException,
			ParserTechnicalException;
}
