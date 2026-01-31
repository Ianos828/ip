package command;

import task.TaskList;

/**
 * Class representing a command to terminate the program.
 */
public class TerminateCommand extends Command {
    /**
     * Constructor for TerminateCommand class
     *
     * @param commandType the type of Command
     */
    public TerminateCommand(CommandType commandType) {
        super(commandType);
    }

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
     * Prints a terminating message.
     *
     * @param taskList list of tasks that commands will operate on
     */
    @Override
    public void execute(TaskList taskList) {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
