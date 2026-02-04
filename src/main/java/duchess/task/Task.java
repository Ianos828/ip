package duchess.task;

import java.time.LocalDate;

/**
 * Class representing a duchess.task.
 */
public abstract class Task {
    protected final String name;
    private boolean complete = false;

    /**
     * Constructor for Task class.
     *
     * @param name the name of the duchess.task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the duchess.task as complete.
     */
    public void markAsComplete() {
        complete = true;
    }

    /**
     * Marks the duchess.task as incomplete.
     */
    public void markAsIncomplete() {
        complete = false;
    }

    public boolean isComplete() {
        return complete;
    }

    protected void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isOutstanding(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the duchess.task.
     *
     * @return a string representation of the duchess.task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", complete ? "X" : " ", name);
    }

    public abstract String toSaveString();
}
