package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

import duchess.parser.Utility;

import duchess.storage.Storage;

import duchess.task.TaskList;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class FindOutstandingCommand extends Command{
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    public FindOutstandingCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException {
        String afterDateAsString = commandArgs.get("/default");

        LocalDate afterDate = Utility.parseDate(afterDateAsString);
        TaskList outstandingTasks = tasks.getOutstandingTasks(afterDate);

        if (outstandingTasks.isEmpty()) {
            return String.format("There are no outstanding tasks after %s!", Utility.formatDate(afterDate));
        }

        return String.format("Here are the outstanding tasks in your list:\n%s", outstandingTasks);
    }
}
