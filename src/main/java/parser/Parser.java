package parser;

import command.*;
import exception.InvalidArgumentException;
import exception.MissingArgumentException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Parser class for parsing user input into commands.
 */
public class Parser {
    /**
     * Returns the type of command of the input based on its string representation.
     *
     * @param input the input string
     * @return the type of command of the input
     */
    private static CommandType getCommandType(String input) {
        return CommandType.getCommandType(input);
    }

    /**
     * Returns a command from the specified input string
     *
     * @param input the user input string
     * @return a command containing its respective arguments
     */
    public static Command getCommand(String input) {
        String[] splitInput = splitIntoPair(input);
        CommandType commandType = Parser.getCommandType(splitInput[0].toLowerCase());

        Command command = null;
        Map<String, String> arguments;

        switch (commandType) {
        case BYE:
            command = new TerminateCommand(commandType);
            break;
        case LIST:
            command = new DisplayListCommand(commandType);
            break;
        case MARK:
            arguments = parseArguments(CompleteTaskCommand.delimiters, splitInput[1]);
            command = new CompleteTaskCommand(commandType, arguments);
            break;
        case UNMARK:
            arguments = parseArguments(UncompleteTaskCommand.delimiters, splitInput[1]);
            command = new UncompleteTaskCommand(commandType, arguments);
            break;
        case DELETE:
            arguments = parseArguments(DeleteTaskCommand.delimiters, splitInput[1]);
            command = new DeleteTaskCommand(commandType, arguments);
            break;
        case DEADLINE:
            arguments = parseArguments(CreateDeadlineCommand.delimiters, splitInput[1]);
            command = new CreateDeadlineCommand(commandType, arguments);
            break;
        case EVENT:
            arguments = parseArguments(CreateEventCommand.delimiters, splitInput[1]);
            command = new CreateEventCommand(commandType, arguments);
            break;
        case TODO:
            arguments = parseArguments(CreateToDoCommand.delimiters, splitInput[1]);
            command = new CreateToDoCommand(commandType, arguments);
            break;
        case UNKNOWN:
            command = new UnknownCommand(commandType);
            break;
        default:
            break;
        }
        return command;
    }

    /**
     * Returns the specified input as an array of length 2, after splitting with whitespace as the delimiter.
     *
     * @param input the input command string from user
     * @return a String array of length 2, with the first item representing the command type and the second representing
     * the rest of the original specified input.
     */
    private static String[] splitIntoPair(String input) {
        String[] splitInput = (input + " ").split(" ", 2);
        splitInput[1] =  splitInput[1].strip().trim();
        return splitInput;
    }

    /**
     * Returns the specified input as a Map with specific delimiter-argument pairs, based on the provided delimiters.
     *
     * <p>
     * Input can have delimiters that are out of order. If multiple delimiters of the same type are in the input, the
     * latest argument for that delimiter will be captured.
     * </p>
     *
     * @param delimiters the delimiters the command expects
     * @param userInput the user input string without the command type
     * @return a map containing delimiter-argument pairs
     */
    private static Map<String, String> parseArguments(Set<String> delimiters, String userInput) {
        String[] argumentComponents = userInput.split(" ");

        Map<String, String> argumentsMap = new HashMap<>();
        StringBuilder currentArgument = new StringBuilder();

        String currentDelimiter = "/default";

        for (String argument : argumentComponents) {
            if (delimiters.contains(argument)) {
                argumentsMap.put(currentDelimiter, currentArgument.toString().strip().trim());
                currentDelimiter = argument;
                currentArgument = new StringBuilder();
            } else {
                currentArgument.append(argument).append(" ");
            }
        }

        argumentsMap.put(currentDelimiter, currentArgument.toString().strip().trim());

        return argumentsMap;
    }

    /**
     * Returns an index to a list as an integer, after extracting the argument from the delimiter-argument pair.
     *
     * @param indexAsString map containing a delimiter-argument pair
     * @return an integer denoting the list index
     * @throws MissingArgumentException if argument is empty string or null
     * @throws InvalidArgumentException if the argument is not a number or multiple numbers are specified
     */
    public static int parseInt(String indexAsString) throws MissingArgumentException, InvalidArgumentException {
        if (indexAsString == null || indexAsString.isEmpty()) {
            throw new MissingArgumentException("No index provided!");
        }

        int index;

        try {
            index = Integer.parseInt(indexAsString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Index provided is not a single number!");
        }

        return index;
    }
}
