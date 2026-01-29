package command;

/**
 * Enum representing the different types of commands.
 */
public enum CommandType {
    BYE("bye"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    TODO("todo"),
    UNMARK("unmark"),
    UNKNOWN(""),
    DELETE("delete");

    public final String input;

    /**
     * Constructor for CommandType enum.
     * @param input the input string
     */
    CommandType(String input) {
        this.input = input;
    }

    /**
     * Returns the command type of the input.
     * @param input the input string
     * @return the command type of the input
     */
    public static CommandType getCommandType(String input) {
        for (CommandType type : CommandType.values()) {
            if (type.input.equals(input)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
