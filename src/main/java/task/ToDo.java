package task;

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
     * Returns a string representation of the todo.
     *
     * @return a string representation of the todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
