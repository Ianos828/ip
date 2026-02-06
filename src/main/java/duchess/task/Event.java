package duchess.task;

import duchess.parser.Utility;
import java.time.LocalDate;

/**
 * Event class for tasks with start and end dates.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor for Event class.
     *
     * @param taskName the name of the task
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     */
    public Event(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for Event class used for loading tasks from storage.
     *
     * @param taskName the name of the task
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     * @param complete the completion status of the task
     */
    public Event(String taskName, LocalDate startDate, LocalDate endDate, boolean complete) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        setComplete(complete);
    }

    /**
     * Returns true if the event is ongoing on the given date.
     *
     * @param date the date to check against
     * @return true if the event is ongoing on the given date, false otherwise
     */
    @Override
    public boolean isOutstanding(LocalDate date) {
        return (date.isAfter(startDate) || date.isEqual(startDate))
                && (date.isBefore(endDate) || date.isEqual(endDate))
                && !isComplete();
    }

    /**
     * Returns a string representation of the event task
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Utility.formatDate(startDate), Utility.formatDate(endDate));
    }

    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s | %s", isComplete() ? "1" : "0", name, startDate, endDate);
    }
}
