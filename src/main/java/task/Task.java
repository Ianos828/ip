package task;

/**
 * Class representing a task.
 */
public abstract class Task {
    protected final TaskType taskType;
    protected final String name;
    protected boolean isComplete = false;

    /**
     * Constructor for Task class.
     *
     * @param name the name of the task
     */
    public Task(TaskType taskType, String name) {
        this.taskType = taskType;
        this.name = name;
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

    public TaskType getType() {
        return taskType;
    };

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete ? "X" : " ", name);
    }

    public abstract String toSaveString();
}
