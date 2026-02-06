package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a command to display the current task list.
 */
public class DisplayListCommand extends Command {
    /**
     * Prints the task list if the list is not empty. Otherwise, print an error message.
     *
     * @param tasks list of tasks that commands will operate on
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "Your list is empty!";
        }

        return String.format("Here are the tasks in your list:\n%s", tasks);
    }
}
