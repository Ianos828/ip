package command;

import task.TaskList;

/**
 * Class representing an unsupported command.
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for UnknownCommand class.
     *
     * @param commandType the type of command
     */
    public UnknownCommand(CommandType commandType) {
        super(commandType);
    }

    /**
     * Prints an error message.
     *
     * @param list list of tasks that commands will operate on
     */
    @Override
    public void execute(TaskList list) {
        System.out.println("I'm sorry, but I don't know what that means :(");
    }
}
