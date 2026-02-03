package parser;

import exception.MangledTaskException;

import task.Task;
import task.Tasktype;
import task.ToDo;
import task.Event;
import task.Deadline;
import utility.Utility;

import java.util.Set;

public class FileParser {
    private static final int TODO_NUMBER_OF_COMPONENTS = 2;
    private static final int DEADLINE_NUMBER_OF_COMPONENTS = 3;
    private static final int EVENT_NUMBER_OF_COMPONENTS = 4;
    private static final Set<String> validCompletionMarkers = Set.of("1", "0");

    public static Task getTask(String rawTask) throws MangledTaskException {
        String[] splitInput = Utility.splitIntoPair(rawTask, " \\| ");
        Tasktype taskType = getTasktype(splitInput[0].toUpperCase());
        String[] taskComponents = splitInput[1].split(" \\| ");

        if (!validCompletionMarkers.contains(taskComponents[0])) {
            throw new MangledTaskException("Invalid task completion marker!", rawTask);
        }

        boolean isComplete = taskComponents[0].equals("1");

        Task task = null;
        String name = taskComponents[1];

        if (Utility.isNotValidName(name)) {
            throw new MangledTaskException("Invalid task name!", rawTask);
        }

        switch(taskType) {
        case TODO:
            if (taskComponents.length != TODO_NUMBER_OF_COMPONENTS) {
                throw new MangledTaskException("Invalid ToDo task format!", rawTask);
            }

            task = new ToDo(name, isComplete);
            break;
        case EVENT:
            if (taskComponents.length != EVENT_NUMBER_OF_COMPONENTS) {
                throw new MangledTaskException("Invalid Event task format!", rawTask);
            }

            String startDate = taskComponents[2];
            String endDate = taskComponents[3];

            if (startDate == null || startDate.isEmpty()) {
                throw new MangledTaskException("Invalid start date!", rawTask);
            }

            if (endDate == null || endDate.isEmpty()) {
                throw new MangledTaskException("Invalid end date!", rawTask);
            }

            task = new Event(name, startDate, endDate, isComplete);
            break;
        case DEADLINE:
            if (taskComponents.length != DEADLINE_NUMBER_OF_COMPONENTS) {
                throw new MangledTaskException("Invalid Deadline task format!", rawTask);
            }

            String deadline = taskComponents[2];

            if (deadline == null || deadline.isEmpty()) {
                throw new MangledTaskException("Invalid deadline!", rawTask);
            }

            task = new Deadline(name, deadline, isComplete);
            break;
        case UNKNOWN:
            throw new MangledTaskException("Unknown task type!", rawTask);
        default:
            break;
        }

        return task;
    }

    private static Tasktype getTasktype(String input) {
        return Tasktype.getCommandType(input);
    }
}
