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
 * Class representing a command to create a task with a deadline.
 */
public class CreateDeadlineCommand extends Command {
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default", "/by");

    /**
     * Constructor for CreateDeadlineCommand class.
     *
     * @param commandArgs a map of delimiter-argument pairs specifying the name and deadline of the task
     */
    public CreateDeadlineCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Extracts the name and deadline from the command and creates a Deadline task with the specified arguments.
     *
     * @param tasks list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name or deadline of the task
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

        return String.format("Got it! I've added this task:\n%s\nNow you have %d task(s) in the list.", deadline, tasks.getSize());
    }
}
