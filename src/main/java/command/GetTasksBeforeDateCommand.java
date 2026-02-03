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

public class GetTasksBeforeDateCommand extends Command{
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    public GetTasksBeforeDateCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    private String getTasksBeforeDate(TaskList tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks available before ").append(date).append(":").append("\n");
        int count = 1;

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            TaskType taskType = task.getType();

            switch (taskType) {
            case DEADLINE:
                LocalDate deadline = ((Deadline) task).getDeadline();
                if (deadline.isBefore(date)) {
                    sb.append(count++).append(". ")
                            .append(task).append("\n");
                }
            break;
            case EVENT:
                LocalDate endDate = ((Event) task).getEndDate();
                if (endDate.isBefore(date)) { //if the event starts and ends before the date
                    sb.append(count++).append(". ")
                            .append(task).append("\n");
                }
            break;
            default:
                break;
            }
        }

        if (count == 1) {
            return String.format("No tasks available before %s!\n", date);
        }

        return sb.toString();
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException {
        String beforeDateAsString = commandArgs.get("/default");

        if (Utility.isInvalidString(beforeDateAsString)) {
            throw new MissingArgumentException("No dates provided! Please specify a date using /before");
        }

        LocalDate beforeDate = Utility.parseDate(beforeDateAsString);
        String tasksBeforeDate = getTasksBeforeDate(tasks, beforeDate);

        System.out.print(tasksBeforeDate);
    }
}
