public enum CommandType {
    BYE("bye"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    TODO("todo"),
    UNMARK("unmark"),
    UNKNOWN("");

    public final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType getCommandType(String command) {
        for (CommandType type : CommandType.values()) {
            if (type.command.equals(command)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
