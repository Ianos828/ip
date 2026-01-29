package duchess;

import command.CommandType;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

import java.util.Scanner;

/**
 * Main class of the application.
 */
public class Duchess {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duchess!");
        System.out.println("What can I do for you?");

        boolean hasNotTerminated = true;

        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();

        while (hasNotTerminated) {
            String input = scanner.nextLine();
            String[] splitInput = input.strip().split(" ", 2);
            CommandType commandType = Parser.getCommandType(splitInput[0].toLowerCase());

            switch (commandType) {
            case BYE:
                hasNotTerminated = false;
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case DEADLINE:
                try {
                    if (splitInput.length < 2) {
                        throw new ArgumentMismatchException("Expected 2 arguments for deadline, received 0 arguments instead.");
                    }
                    String[] taskInstructions = splitInput[1].split(" /by ");
                    if (taskInstructions.length != 2) {
                        throw new ArgumentMismatchException(String.format("Expected 2 arguments for deadline, received %d argument(s) instead.", taskInstructions.length));
                    }
                    Deadline deadline = new Deadline(taskInstructions[0], taskInstructions[1]);
                    list.addTask(deadline);
                } catch (ArgumentMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter a deadline in the format \"task_name /by deadline_date\"!");
                }
                break;
            case EVENT:
                try {
                    if (splitInput.length < 2) {
                        throw new ArgumentMismatchException("Expected 3 arguments for event, received 0 arguments instead.");
                    }
                    String[] taskInstructions = splitInput[1].split(" /from | /to ");
                    if (taskInstructions.length != 3) {
                        throw new ArgumentMismatchException(String.format("Expected 3 arguments for event, received %d argument(s) instead.", taskInstructions.length));
                    }
                    Event event = new Event(taskInstructions[0], taskInstructions[1], taskInstructions[2]);
                    list.addTask(event);
                } catch (ArgumentMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter an event in the format \"task_name /from start_date /to end_date\"!");
                }
                break;
            case LIST:
                if (list.isEmpty()) {
                    System.out.println("Your list is empty!");
                    break;
                }
                System.out.print(list);
                break;
            case MARK:
                try {
                    if (splitInput.length < 2) {
                        throw new ArgumentMismatchException("Expected 1 argument for mark, received 0 arguments instead.");
                    }
                    String[] taskInstructions = splitInput[1].split(" ");
                    if (taskInstructions.length != 1) {
                        throw new ArgumentMismatchException(String.format("Expected 1 argument for mark, received %d argument(s) instead.", taskInstructions.length));
                    }
                    list.markTaskAsComplete(Integer.parseInt(taskInstructions[0]));
                } catch (ArgumentMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter a mark command in the format \"mark list_index\"!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The specified task index does not exist in the list.");
                }
                break;
            case TODO:
                try {
                    if (splitInput.length < 2) {
                        throw new ArgumentMismatchException("Expected 1 argument for todo, received 0 arguments instead.");
                    }
                    ToDo todo = new ToDo(splitInput[1]);
                    list.addTask(todo);
                } catch (ArgumentMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("The description of a todo cannot be empty.");
                }
                break;
            case UNMARK:
                try {
                    if (splitInput.length < 2) {
                        throw new ArgumentMismatchException("Expected 1 argument for unmark, received 0 arguments instead.");
                    }
                    String[] taskInstructions = splitInput[1].split(" ");
                    if (taskInstructions.length != 1) {
                        throw new ArgumentMismatchException(String.format("Expected 1 argument for unmark, received %d argument(s) instead.", taskInstructions.length));
                    }
                    list.markTaskAsIncomplete(Integer.parseInt(taskInstructions[0]));
                } catch (ArgumentMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter an unmark command in the format \"unmark list_index\"!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The specified task index does not exist in the list.");
                }
                break;
            case UNKNOWN:
                System.out.println("I'm sorry, but I don't know what that means :(");
                break;
            case DELETE:
                try {
                    if (splitInput.length < 2) {
                        throw new ArgumentMismatchException("Expected 1 argument for delete, received 0 arguments instead.");
                    }
                    String[] taskInstructions = splitInput[1].split(" ");
                    if (taskInstructions.length != 1) {
                        throw new ArgumentMismatchException(String.format("Expected 1 argument for unmark, received %d argument(s) instead.", taskInstructions.length));
                    }
                    list.removeTask(Integer.parseInt(taskInstructions[0]));
                } catch (ArgumentMismatchException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter a delete command in the format \"delete list_index\"!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The specified task index does not exist in the list.");
                }
                break;
            default:
                break;
            }
        }
    }
}

/**
 * Exception thrown when the number of arguments provided to a command does not match the expected number.
 */
class ArgumentMismatchException extends Exception {
    public ArgumentMismatchException(String message) {
        super(message);
    }
}