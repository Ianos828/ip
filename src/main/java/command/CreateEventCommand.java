package command;

import exception.MissingArgumentException;

import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import utility.Utility;

import java.util.Map;
import java.util.Set;

/**
 * Class representing a command to create an event.
 */
public class CreateEventCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default", "/from", "/to");

    /**
     * Constructor for CreateEventCommand class.
     *
     * @param type the type of command
     * @param commandArgs a map of delimiter-argument pairs specifying the name, start and end dates of the task
     */
    public CreateEventCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name, start and end dates from the command and creates an Event task with the specified arguments.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name, start or end dates of the task
     */
    public void execute(TaskList tasks, Storage storage) throws MissingArgumentException {
        String name = commandArgs.get("/default");
        String startDate = commandArgs.get("/from");
        String endDate = commandArgs.get("/to");

        if (Utility.isNotValidName(name)) {
            throw new MissingArgumentException("Event name cannot be empty!");
        }

        if (startDate == null || startDate.isEmpty()) {
            throw new MissingArgumentException("Event start date cannot be empty!");
        }

        if (endDate == null || endDate.isEmpty()) {
            throw new MissingArgumentException("Event end date cannot be empty!");
        }

        Task event = new Event(name, startDate, endDate);
        tasks.addTask(event);
        tasks.printSuccessMessage(event);
        storage.saveTasksToFile(tasks);
    }
}
