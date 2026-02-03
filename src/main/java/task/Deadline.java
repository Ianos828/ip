package task;

import parser.Utility;

import java.time.LocalDate;

/**
 * Deadline class for tasks with deadlines.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param taskName the name of the task
     * @param deadline the deadline for the task
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(String taskName, LocalDate deadline, boolean isComplete) {
        super(taskName);
        this.deadline = deadline;
        this.isComplete = isComplete;
    }

    /**
     * Returns a string representation of the deadline task
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Utility.formatDate(deadline));
    }

    @Override
    public String toSaveString() {
        return String.format("D | %s | %s | %s", isComplete ? "1" : "0", name, deadline);
    }
}
