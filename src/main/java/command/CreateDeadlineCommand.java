package command;

import exception.MissingArgumentException;
import task.Deadline;
import task.Task;
import task.TaskList;

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
     * @param list list of tasks that commands will operate on
     * @throws MissingArgumentException if the user does not specify the name or deadline of the task
     */
    @Override
    public void execute(TaskList list) throws MissingArgumentException {
        String name = commandArgs.get("/default");
        String endDate = commandArgs.get("/by");

        if (name == null || name.isEmpty()) {
            throw new MissingArgumentException("Task name cannot be empty!");
        }

        if (endDate == null || endDate.isEmpty()) {
            throw new MissingArgumentException("Please provide deadline for task!");
        }

        Task deadline = new Deadline(name, endDate);
        list.addTask(deadline);
    }
}
