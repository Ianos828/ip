import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        boolean hasNotTerminated = true;

        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();

        while (hasNotTerminated) {
            String input = scanner.nextLine().toLowerCase();
            String[] splitInput = input.strip().split(" ", 2);
            CommandType commandType = Parser.getCommandType(splitInput[0]);

            switch (commandType) {
                case BYE:
                    hasNotTerminated = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case DEADLINE:
                    try {
                        String[] taskInstructions = splitInput[1].split(" /by ", 2);
                        DeadlineTask deadlineTask = new DeadlineTask(taskInstructions[0], taskInstructions[1]);
                        list.addTask(deadlineTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadlineTask);
                        System.out.printf("Now you have %d tasks in the list.\n", list.getSize());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please enter a deadline in the format \"task_name /by deadline_date\"!");
                    }
                    break;
                case EVENT:
                    try {
                        String[] taskInstructions = splitInput[1].split(" /from | /to ", 3);
                        EventTask eventTask = new EventTask(taskInstructions[0], taskInstructions[1], taskInstructions[2]);
                        list.addTask(eventTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(eventTask);
                        System.out.printf("Now you have %d tasks in the list.\n", list.getSize());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please enter an event in the format \"task_name /from start_date /to end_date\"!");
                    }
                    break;
                case LIST:
                    System.out.print(list);
                    break;
                case MARK:
                    try {
                        list.markTaskAsComplete(Integer.parseInt(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        continue;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number!");
                        continue;
                    }
                    break;
                case TODO:
                    try {
                        ToDoTask todoTask = new ToDoTask(splitInput[1]);
                        list.addTask(todoTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todoTask);
                        System.out.printf("Now you have %d tasks in the list.\n", list.getSize());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case UNMARK:
                    try {
                        list.markTaskAsIncomplete(Integer.parseInt(splitInput[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        continue;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number!");
                        continue;
                    }
                    break;
                case UNKNOWN:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                default:
                    break;
            }
        }
    }
}
