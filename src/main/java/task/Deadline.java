package task;

/**
 * Deadline class for tasks with deadlines.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param taskName the name of the task
     * @param deadline the deadline for the task
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
