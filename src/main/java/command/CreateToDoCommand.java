package command;

import exception.MissingArgumentException;

import parser.Utility;

import storage.Storage;

import task.Task;
import task.TaskList;
import task.ToDo;

import java.util.Map;
import java.util.Set;

/**
 * Class representing a command to create a todo task.
 */
public class CreateToDoCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    /**
     * Constructor for CreateToDoCommand class.
     *
     * @param type the type of command
     * @param commandArgs a map with a single delimiter-argument pair specifying the name of the task
     */
    public CreateToDoCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name from the command and creates a todo task with the specified argument.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name of the task
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingArgumentException {
        String name = commandArgs.get("/default");

        if (Utility.isInvalidString(name)) {
            throw new MissingArgumentException("Task name cannot be empty!");
        }

        Task toDo = new ToDo(name);

        tasks.addTask(toDo);
        tasks.printSuccessMessage(toDo);
        storage.saveTasksToFile(tasks);
    }
}
