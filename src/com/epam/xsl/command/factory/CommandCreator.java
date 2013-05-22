package com.epam.xsl.command.factory;

import javax.servlet.http.HttpServletRequest;

import com.epam.xsl.command.Command;
import com.epam.xsl.command.NoCommand;
import com.epam.xsl.command.SubcategoriesCommand;


public class CommandCreator {

	private static final String ATTR_NAME_COMMAND = "command";

	private CommandCreator() {
	}

	public static Command createCommand(HttpServletRequest request) {
		CommandEnum commandEnum = getCommandEnum((String) request
				.getParameter(ATTR_NAME_COMMAND));
		switch (commandEnum) {
		case SUBCATEGORIES:
			return new SubcategoriesCommand();
		default:
			return new NoCommand();
		}
	}

	private static CommandEnum getCommandEnum(String command) {
		if (command == null) {
			return CommandEnum.NO_COMMAND;
		} else if (isWrongCommandName(command)) {
			return CommandEnum.NO_COMMAND;
		} else {
			return CommandEnum.valueOf(command);
		}
	}

	private static boolean isWrongCommandName(String command) {
		CommandEnum[] realCommandNames = CommandEnum.values();
		for (CommandEnum realCommandName : realCommandNames) {
			if (realCommandName.toString().equals(command)) {
				return false;
			}
		}
		return true;
	}
}
