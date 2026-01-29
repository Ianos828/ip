package task;

/**
 * Class representing a task.
 */
public class Task {
    private final String name;
    private boolean isComplete;

    /**
     * Constructor for Task class.
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        isComplete = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        isComplete = false;
    }

    /**
     * Returns a string representation of the task.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete ? "X" : " ", name);
    }
}
