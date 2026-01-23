import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsComplete(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsComplete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void markTaskAsIncomplete(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid task index!");
        }

        Task task = tasks.get(index - 1);
        task.markAsIncomplete();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    private boolean isValidIndex(int index) {
        return index > 0 && index <= tasks.size();
    }

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
