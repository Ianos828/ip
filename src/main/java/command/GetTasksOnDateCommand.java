package command;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;

import parser.Utility;

import storage.Storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.TaskType;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class GetTasksOnDateCommand extends Command{
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    public GetTasksOnDateCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    private String getTasksOnDate(TaskList tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Notable tasks on ").append(date).append(":").append("\n");
        int count = 1;

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            TaskType taskType = task.getType();

            switch (taskType) {
            case DEADLINE:
                LocalDate deadline = ((Deadline) task).getDeadline();
                if (deadline.equals(date)) {
                    sb.append(count++).append(". ")
                            .append(task).append("\n");
                }
                break;
            case EVENT:
                LocalDate startDate = ((Event) task).getStartDate();
                if (startDate.isAfter(date)) {
                    sb.append(count++).append(". ")
                            .append(task).append("\n");
                }
                break;
            default:
                break;
            }
        }

        if (count == 1) {
            return String.format("No notable tasks on %s!\n", date);
        }

        return sb.toString();
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException {
        String onDateAsString = commandArgs.get("/default");

        if (Utility.isInvalidString(onDateAsString)) {
            throw new MissingArgumentException("No dates provided! Please specify a date using /on");
        }

        LocalDate onDate = Utility.parseDate(onDateAsString);
        String tasksOnDate = getTasksOnDate(tasks, onDate);

        System.out.print(tasksOnDate);
    }
}
