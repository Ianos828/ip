package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

import duchess.parser.Utility;

import duchess.storage.Storage;

import duchess.task.Task;
import duchess.task.TaskList;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a duchess.command to mark a duchess.task as uncompleted.
 */
public class MarkTaskIncompleteCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    /**
     * Constructor for MarkTaskIncompleteCommand class.
     *
     * @param commandType the type of duchess.command
     * @param commandArgs a map with a single delimiter-argument pair representing a list index
     */
    public MarkTaskIncompleteCommand(CommandType commandType, Map<String, String> commandArgs) {
        super(commandType);
        this.commandArgs = commandArgs;
    }

    /**
     * Marks the duchess.task at the specified index as uncompleted in the specified duchess.task list.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if no list index is provided
     * @throws InvalidArgumentException if the index provided is not a single number
     */
    @Override
    public String execute (TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException, IOException {
        String indexAsString = commandArgs.get("/default");
        int index = Utility.parseInt(indexAsString);
        Task task = tasks.markTaskAsIncomplete(index);
        storage.saveTasksToFile(tasks);

        return String.format("OK, I've marked this duchess.task as not done yet:\n%s", task);
    }
}
