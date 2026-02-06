package duchess.task;

/**
 * Class representing a task with a name and no additional information.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     *
     * @param name the name of the ToDo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor for ToDo class used for loading tasks from storage.
     *
     * @param name the name of the ToDo task
     * @param complete the completion status of the task
     */
    public ToDo(String name, boolean complete) {
        super(name);
        setComplete(complete);
    }

    /**
     * Returns a string representation of the ToDo.
     *
     * @return a string representation of the ToDo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a string representation of the ToDo task for saving to storage.
     *
     * @return a string representation of the ToDo task for saving to storage
     */
    @Override
    public String toSaveString() {
        return String.format("T | %s | %s", isComplete() ? "1" : "0", name);
    }
}
