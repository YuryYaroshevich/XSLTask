package com.epam.xsl.command;

import javax.servlet.http.HttpServletRequest;

public final class CommandCreator {
	private static final String ATTR_NAME_COMMAND = "command";
	
	private static enum CommandEnum {
		CATEGORIES_COMMAND, SUBCATEGORIES, GOODS, ADD_GOOD,
		SAVE_GOOD, CANCEL
	}

	private CommandCreator() {
	}

	public static Command createCommand(HttpServletRequest request) {
		CommandEnum commandEnum = getCommandEnum((String) request
				.getParameter(ATTR_NAME_COMMAND));
		switch (commandEnum) {
		case SUBCATEGORIES:
			return SubcategoriesCommand.getInstance();
		case GOODS:
			return GoodsCommand.getInstance();
		case ADD_GOOD:
			return AddGoodCommand.getInstance();
		case SAVE_GOOD: 
			return SaveGoodCommand.getInstance();
		case CANCEL: 
			return CancelCommand.getInstance();
		default:
			return CategoriesCommand.getInstance();
		}
	}

	private static CommandEnum getCommandEnum(String command) {
		if (command == null) {
			return CommandEnum.CATEGORIES_COMMAND;
		} else if (isWrongCommandName(command)) {
			return CommandEnum.CATEGORIES_COMMAND;
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
