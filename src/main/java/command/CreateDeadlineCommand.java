package command;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;

import parser.Utility;

import storage.Storage;

import task.Deadline;
import task.Task;
import task.TaskList;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a command to create a task with a deadline.
 */
public class CreateDeadlineCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default", "/by");

    /**
     * Constructor for CreateDeadlineCommand class.
     *
     * @param type the type of command
     * @param commandArgs a map of delimiter-argument pairs specifying the name and deadline of the task
     */
    public CreateDeadlineCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name and deadline from the command and creates a Deadline task with the specified arguments.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name or deadline of the task
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws InvalidArgumentException, MissingArgumentException {
        String name = commandArgs.get("/default");
        String endDateAsString = commandArgs.get("/by");

        if (Utility.isInvalidString(name)) {
            throw new MissingArgumentException("Task name cannot be empty!");
        }

        LocalDate endDate = Utility.parseDate(endDateAsString);

        Task deadline = new Deadline(name, endDate);

        tasks.addTask(deadline);
        tasks.printSuccessMessage(deadline);
        storage.saveTasksToFile(tasks);
    }
}
