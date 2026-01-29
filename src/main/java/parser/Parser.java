package parser;

import command.CommandType;

/**
 * Parser class for parsing user input into commands.
 */
public class Parser {
    /**
     * Returns the command type of the input based on the first word, separated by a space.
     * @param input the input string
     * @return the command type of the input
     */
    public static CommandType getCommandType(String input) {
        return CommandType.getCommandType(input);
    }
}
