package duchess.command;

import duchess.storage.Storage;

import duchess.task.TaskList;

/**
 * Class representing a command to terminate the program.
 */
public class TerminateCommand extends Command {
    /**
     * Returns true to terminate the main program.
     *
     * @return a boolean to terminate the main program
     */
    @Override
    public boolean isTerminatingCommand() {
        return true;
    }

    /**
     * Returns a terminating message.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
