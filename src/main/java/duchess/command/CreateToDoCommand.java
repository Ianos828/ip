package duchess.command;

import duchess.exception.MissingArgumentException;

import duchess.parser.Utility;

import duchess.storage.Storage;

import duchess.task.Task;
import duchess.task.TaskList;
import duchess.task.ToDo;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a duchess.command to create a todo duchess.task.
 */
public class CreateToDoCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    /**
     * Constructor for CreateToDoCommand class.
     *
     * @param type the type of duchess.command
     * @param commandArgs a map with a single delimiter-argument pair specifying the name of the duchess.task
     */
    public CreateToDoCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name from the duchess.command and creates a todo duchess.task with the specified argument.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name of the duchess.task
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MissingArgumentException, IOException {
        String name = commandArgs.get("/default");

        if (Utility.isInvalidString(name)) {
            throw new MissingArgumentException("Task name cannot be empty!");
        }

        Task toDo = new ToDo(name);

        tasks.addTask(toDo);
        storage.saveTasksToFile(tasks);

        return String.format("Got it! I've added this duchess.task:\n%s\nNow you have %d duchess.task(s) in the list.", toDo, tasks.getSize());
    }
}
