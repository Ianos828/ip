package command;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;

import parser.Utility;

import storage.Storage;

import task.TaskList;

import java.util.Map;
import java.util.Set;

/**
 * Class representing a command to mark a task as uncompleted.
 */
public class MarkTaskIncompleteCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    /**
     * Constructor for MarkTaskIncompleteCommand class.
     *
     * @param commandType the type of command
     * @param commandArgs a map with a single delimiter-argument pair representing a list index
     */
    public MarkTaskIncompleteCommand(CommandType commandType, Map<String, String> commandArgs) {
        super(commandType);
        this.commandArgs = commandArgs;
    }

    /**
     * Marks the task at the specified index as uncompleted in the specified task list.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if no list index is provided
     * @throws InvalidArgumentException if the index provided is not a single number
     */
    @Override
    public void execute (TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException {
        String indexAsString = commandArgs.get("/default");
        int index = Utility.parseInt(indexAsString);
        tasks.markTaskAsIncomplete(index);
        storage.saveTasksToFile(tasks);
    }
}
