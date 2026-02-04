package duchess.task;

/**
 * Class representing a duchess.task with a name and no additional information.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     *
     * @param name the name of the ToDo duchess.task
     */
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean complete) {
        super(name);
        setComplete(complete);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return a string representation of the todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toSaveString() {
        return String.format("T | %s | %s", isComplete() ? "1" : "0", name);
    }
}
