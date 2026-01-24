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

    CommandType(String input) {
        this.input = input;
    }

    public static CommandType getCommandType(String input) {
        for (CommandType type : CommandType.values()) {
            if (type.input.equals(input)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
