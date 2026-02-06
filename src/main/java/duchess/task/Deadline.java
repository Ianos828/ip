package duchess.task;

import duchess.parser.Utility;

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

    /**
     * Constructor for Deadline class used for loading tasks from storage.
     *
     * @param taskName the name of the task
     * @param deadline the deadline for the task
     * @param complete the completion status of the task
     */
    public Deadline(String taskName, LocalDate deadline, boolean complete) {
        super(taskName);
        this.deadline = deadline;
        setComplete(complete);
    }

    /**
     * Returns true if the task has yet to be completed before its deadline on the given date.
     *
     * @param date the date to check against
     * @return true if the deadline task is outstanding, false otherwise
     */
    @Override
    public boolean isOutstanding(LocalDate date) {
        return deadline.isAfter(date) && !isComplete();
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

    /**
     * Returns a string representation of the deadline task for saving to storage.
     *
     * @return a string representation of the deadline task for saving to storage
     */
    @Override
    public String toSaveString() {
        return String.format("D | %s | %s | %s", isComplete() ? "1" : "0", name, deadline);
    }
}
