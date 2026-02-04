package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

import duchess.parser.Utility;

import duchess.storage.Storage;

import duchess.task.Deadline;
import duchess.task.Task;
import duchess.task.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a duchess.command to create a duchess.task with a deadline.
 */
public class CreateDeadlineCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default", "/by");

    /**
     * Constructor for CreateDeadlineCommand class.
     *
     * @param type the type of duchess.command
     * @param commandArgs a map of delimiter-argument pairs specifying the name and deadline of the duchess.task
     */
    public CreateDeadlineCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name and deadline from the duchess.command and creates a Deadline duchess.task with the specified arguments.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name or deadline of the duchess.task
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException, IOException {
        String name = commandArgs.get("/default");
        String endDateAsString = commandArgs.get("/by");

        if (Utility.isInvalidString(name)) {
            throw new MissingArgumentException("Task name cannot be empty!");
        }

        LocalDate endDate = Utility.parseDate(endDateAsString);

        Task deadline = new Deadline(name, endDate);

        tasks.addTask(deadline);
        storage.saveTasksToFile(tasks);

        return String.format("Got it! I've added this duchess.task:\n%s\nNow you have %d duchess.task(s) in the list.", deadline, tasks.getSize());
    }
}
