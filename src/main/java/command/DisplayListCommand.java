package command;

import task.TaskList;

/**
 * Class representing a command to display the current task list.
 */
public class DisplayListCommand extends Command {
    /**
     * Constructor for DisplayListCommand class.
     *
     * @param commandType the type of command
     */
    public DisplayListCommand(CommandType commandType) {
        super(commandType);
    }

    /**
     * Prints the task list if the list is not empty. Otherwise, print an error message.
     *
     * @param list list of tasks that commands will operate on
     */
    @Override
    public void execute(TaskList list) {
        if (list.isEmpty()) {
            System.out.println("Your list is empty!");
            return;
        }
        System.out.print(list);
    }
}
