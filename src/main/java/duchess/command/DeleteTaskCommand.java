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
 * Class representing a duchess.command to delete a duchess.task from a duchess.task list.
 */
public class DeleteTaskCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    /**
     * Constructor for DeleteTaskCommand class.
     *
     * @param commandType the type of duchess.command
     * @param commandArgs a map with a single delimiter-argument pair representing a list index
     */
    public DeleteTaskCommand(CommandType commandType, Map<String, String> commandArgs) {
        super(commandType);
        this.commandArgs = commandArgs;
    }

    /**
     * Deletes the duchess.task at the specified index in the specified duchess.task list.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if no list index is provided
     * @throws InvalidArgumentException if the index provided is not a single number
     */
    @Override
    public String execute (TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException, IOException {
        String indexAsString = commandArgs.get("/default");
        int index = Utility.parseInt(indexAsString);
        Task task = tasks.removeTask(index);
        storage.saveTasksToFile(tasks);

        return String.format("Noted. I've removed this duchess.task:\n%s\nNow you have %d duchess.task(s) in the list.", task, tasks.getSize());
    }
}
