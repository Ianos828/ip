package task;

public enum TaskType {
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
    TaskType(String input) {
        this.input = input;
    }

    /**
     * Returns the command type of the input.
     *
     * @param input the input string
     * @return the command type of the input
     */
    public static TaskType getCommandType(String input) {
        for (TaskType type : TaskType.values()) {
            if (type.input.equals(input)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
