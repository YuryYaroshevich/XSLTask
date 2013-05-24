package com.epam.xsl.command.addentity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.exception.CommandException;

public class AddGoodCommand extends Command {

	@Override
	public Transformer execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		
		return null;
	}

}
