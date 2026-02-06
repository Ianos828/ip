package duchess.command;

import duchess.exception.MissingArgumentException;
import duchess.parser.Utility;
import duchess.storage.Storage;
import duchess.task.TaskList;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a command to find tasks by keyword.
 */
public class FindTaskCommand extends Command{
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    /**
     * Constructor for FindTaskCommand class.
     * @param commandArgs a map with a single delimiter-argument pair representing a keyword
     */
    public FindTaskCommand(Map<String, String> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Finds tasks matching the specified keyword.
     *
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return message to be displayed to the user
     * @throws MissingArgumentException if no keyword is provided
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MissingArgumentException {
        String keyword = commandArgs.get("/default");

        if (Utility.isInvalidString(keyword)) {
            throw new MissingArgumentException("No keyword provided!");
        }

        TaskList matchedTasks = tasks.findMatchingTasks(keyword.toLowerCase());

        if (matchedTasks.isEmpty()) {
            return "No matching tasks found!";
        }

        return String.format("Here are the matching tasks in your list:\n%s",
                matchedTasks);
    }
}
