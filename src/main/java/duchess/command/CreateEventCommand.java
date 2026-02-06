package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

import duchess.parser.Utility;

import duchess.storage.Storage;

import duchess.task.Event;
import duchess.task.Task;
import duchess.task.TaskList;

import java.io.IOException;
import java.time.LocalDate;
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
     * @param commandArgs a map of delimiter-argument pairs specifying the name, start and end dates of the task
     */
    public CreateEventCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name, start and end dates from the command and creates an Event task with the specified arguments.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name, start or end dates of the task
     */
    public String execute(TaskList tasks, Storage storage) throws InvalidArgumentException, MissingArgumentException, IOException {
        String name = commandArgs.get("/default");
        String startDateAsString = commandArgs.get("/from");
        String endDateAsString = commandArgs.get("/to");

        if (Utility.isInvalidString(name)) {
            throw new MissingArgumentException("Event name cannot be empty!");
        }

        LocalDate startDate = Utility.parseDate(startDateAsString);
        LocalDate endDate = Utility.parseDate(endDateAsString);

        if (startDate.isAfter(endDate)) {
            throw new InvalidArgumentException("Start date cannot be after end date!");
        }

        Task event = new Event(name, startDate, endDate);

        tasks.addTask(event);
        storage.saveTasksToFile(tasks);

        return String.format("Got it! I've added this task:\n%s\nNow you have %d task(s) in the list.", event, tasks.getSize());
    }
}
