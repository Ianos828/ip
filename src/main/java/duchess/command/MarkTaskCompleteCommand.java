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
 * Class representing a command to mark a task as completed.
 */
public class MarkTaskCompleteCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    /**
     * Constructor for MarkTaskCompleteCommand class.
     *
     * @param commandType the type of command
     * @param commandArgs a map with a single delimiter-argument pair representing a list index
     */
    public MarkTaskCompleteCommand(CommandType commandType, Map<String, String> commandArgs) {
        super(commandType);
        this.commandArgs = commandArgs;
    }

    /**
     * Marks the task at the specified index as completed in the specified task list.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the index
     * @throws InvalidArgumentException if the index provided is not a single number
     */
    @Override
    public String execute (TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException, IOException {
        String indexAsString = commandArgs.get("/default");
        int index = Utility.parseInt(indexAsString);
        Task task = tasks.markTaskAsComplete(index);
        storage.saveTasksToFile(tasks);

        return String.format("Nice! I've marked this task as done:\n%s", task);
    }
}
