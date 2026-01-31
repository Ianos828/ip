package task;

/**
 * Event class for tasks with start and end dates.
 */
public class Event extends Task {
    private final String startDate;
    private final String endDate;

    /**
     * Constructor for Event class.
     *
     * @param taskName the name of the task
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     */
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the event task
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startDate, endDate);
    }
}
