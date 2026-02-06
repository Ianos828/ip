package duchess.task;

import duchess.exception.InvalidArgumentException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a list of tasks.
 */
public class TaskList{
    private final List<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class used for loading tasks from storage.
     *
     * @param tasks the list of tasks to load
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index the index of the task to remove
     * @return the removed task
     * @throws InvalidArgumentException if the index is invalid
     */
    public Task removeTask(int index) throws InvalidArgumentException {
        if (isInvalidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    /**
     * Marks a task as complete.
     *
     * @param index the index of the task to mark as complete
     * @return the marked task
     * @throws InvalidArgumentException if the index is invalid
     */
    public Task markTaskAsComplete(int index) throws InvalidArgumentException {
        if (isInvalidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsComplete();
        return task;
    }

    /**
     * Marks a task as incomplete.
     *
     * @param index the index of the task to mark as incomplete
     * @return the marked task
     * @throws InvalidArgumentException if the index is invalid
     */
    public Task markTaskAsIncomplete(int index) throws InvalidArgumentException {
        if (isInvalidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsIncomplete();
        return task;
    }

    /**
     * Checks if the specified index is valid.
     *
     * @param index the index to check
     * @return true if the index is valid, false otherwise
     */
    private boolean isInvalidIndex(int index) {
        return index <= 0 || index > tasks.size();
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks for outstanding tasks on a given date.
     *
     * @param date the date to check against
     * @return a list of outstanding tasks
     */
    public TaskList getOutstandingTasks(LocalDate date) {
        List<Task> outstandingTasks = tasks.stream()
                .filter(task -> task.isOutstanding(date))
                .toList();

        return new TaskList(outstandingTasks);
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int taskIndex = 1;

        for (Task task : tasks) {
            sb.append(String.format("%d. %s\n", taskIndex++, task.toString()));
        }

        return sb.toString().strip().trim();
    }

    /**
     * Returns a string representation of the list for saving to storage.
     *
     * @return a string representation of the list for saving to storage
     */
    public String toSaveString() {
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append(task.toSaveString()).append("\n");
        }

        return sb.toString().strip().trim();
    }
}
