package duchess.parser;

import duchess.command.Command;
import duchess.command.CommandType;
import duchess.command.CreateDeadlineCommand;
import duchess.command.CreateEventCommand;
import duchess.command.CreateToDoCommand;
import duchess.command.DeleteTaskCommand;
import duchess.command.DisplayListCommand;
import duchess.command.FindOutstandingCommand;
import duchess.command.MarkTaskCompleteCommand;
import duchess.command.MarkTaskIncompleteCommand;
import duchess.command.TerminateCommand;
import duchess.command.UnknownCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * CommandParser class for parsing user input into commands.
 */
public class CommandParser {
    /**
     * Returns the command type of the input based on its string representation.
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
        String[] splitInput = Utility.splitIntoPair(input, " ");
        CommandType commandType = CommandParser.getCommandType(splitInput[0].toLowerCase());

        Command command = null;
        Map<String, String> arguments;

        switch (commandType) {
        case BYE:
            command = new TerminateCommand();
            break;
        case LIST:
            command = new DisplayListCommand();
            break;
        case MARK:
            arguments = parseArguments(MarkTaskCompleteCommand.delimiters, splitInput[1]);
            command = new MarkTaskCompleteCommand(arguments);
            break;
        case UNMARK:
            arguments = parseArguments(MarkTaskIncompleteCommand.delimiters, splitInput[1]);
            command = new MarkTaskIncompleteCommand(arguments);
            break;
        case DELETE:
            arguments = parseArguments(DeleteTaskCommand.delimiters, splitInput[1]);
            command = new DeleteTaskCommand(arguments);
            break;
        case DEADLINE:
            arguments = parseArguments(CreateDeadlineCommand.delimiters, splitInput[1]);
            command = new CreateDeadlineCommand(arguments);
            break;
        case EVENT:
            arguments = parseArguments(CreateEventCommand.delimiters, splitInput[1]);
            command = new CreateEventCommand(arguments);
            break;
        case TODO:
            arguments = parseArguments(CreateToDoCommand.delimiters, splitInput[1]);
            command = new CreateToDoCommand(arguments);
            break;
        case OUTSTANDING:
            arguments = parseArguments(FindOutstandingCommand.delimiters, splitInput[1]);
            command = new FindOutstandingCommand(arguments);
            break;
        case UNKNOWN:
            command = new UnknownCommand();
            break;
        default:
            break;
        }
        return command;
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
}
