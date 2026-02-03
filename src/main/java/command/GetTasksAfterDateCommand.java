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

public class GetTasksAfterDateCommand extends Command{
    private final Map<String, String> commandArgs;
    public static final Set<String> delimiters = Set.of("/default");

    public GetTasksAfterDateCommand(CommandType type, Map<String, String> commandArgs) {
        super(type);
        this.commandArgs = commandArgs;
    }

    private String getTasksAfterDate(TaskList tasks, LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Tasks available after ").append(date).append(":").append("\n");
        int count = 1;

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            TaskType taskType = task.getType();

            switch (taskType) {
            case DEADLINE:
                LocalDate deadline = ((Deadline) task).getDeadline();
                if (deadline.isAfter(date)) {
                    sb.append(count++).append(". ")
                            .append(task).append("\n");
                }
                break;
            case EVENT:
                LocalDate startDate = ((Event) task).getStartDate();
                LocalDate endDate = ((Event) task).getEndDate();
                if (startDate.isBefore(date) && endDate.isAfter(date)) {
                    sb.append(count++).append(". ")
                            .append(task).append("\n");
                }
                break;
            default:
                break;
            }
        }

        if (count == 1) {
            return String.format("No tasks available after %s!\n", date);
        }

        return sb.toString();
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingArgumentException, InvalidArgumentException {
        String afterDateAsString = commandArgs.get("/default");

        LocalDate afterDate = Utility.parseDate(afterDateAsString);
        String tasksAfterDate = getTasksAfterDate(tasks, afterDate);

        System.out.print(tasksAfterDate);
    }
}
