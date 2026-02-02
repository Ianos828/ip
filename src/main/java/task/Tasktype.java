package task;

public enum Tasktype {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T"),
    UNKNOWN("");


    public final String input;

    /**
     * Constructor for CommandType enum.
     *
     * @param input the input string
     */
    Tasktype(String input) {
        this.input = input;
    }

    /**
     * Returns the command type of the input.
     *
     * @param input the input string
     * @return the command type of the input
     */
    public static Tasktype getCommandType(String input) {
        for (Tasktype type : Tasktype.values()) {
            if (type.input.equals(input)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
