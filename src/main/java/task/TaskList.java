package task;

import exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        printSuccessMessage(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index the index of the task to remove
     */
    public void removeTask(int index) throws InvalidArgumentException {
        if (!isValidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.printf("Now you have %d task(s) in the list.\n", getSize());
    }

    /**
     * Marks a task as complete.
     *
     * @param index the index of the task to mark as complete
     */
    public void markTaskAsComplete(int index) throws InvalidArgumentException {
        if (!isValidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsComplete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Marks a task as incomplete.
     *
     * @param index the index of the task to mark as incomplete
     */
    public void markTaskAsIncomplete(int index) throws InvalidArgumentException {
        if (!isValidIndex(index)) {
            throw new InvalidArgumentException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsIncomplete();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Checks if the specified index is valid.
     *
     * @param index the index to check
     * @return  true if the index is valid, false otherwise
     */
    private boolean isValidIndex(int index) {
        return index > 0 && index <= tasks.size();
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
     * Prints a success message when a task is added to the list.
     *
     * @param task the task that was added
     */
    private void printSuccessMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d task(s) in the list.\n", getSize());
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
        sb.append("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            sb.append(String.format("%d. %s\n", taskIndex, task.toString()));
            taskIndex++;
        }

        return sb.toString();
    }
}
