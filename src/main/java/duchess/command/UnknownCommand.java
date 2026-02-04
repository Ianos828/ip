package duchess.command;

import duchess.storage.Storage;

import duchess.task.TaskList;

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
     * @param tasks list of tasks that commands will operate on
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "I'm sorry, but I don't know what that means :(";
    }
}
