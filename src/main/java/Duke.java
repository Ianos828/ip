import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();

        while (true) {
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("I'm sorry, I didn't quite get that. Can you please repeat?");
                continue;
            }

            String[] splitInput = input.strip().split(" ");

            if (splitInput.length == 1) {
                if (input.equals("bye")) {
                    break;
                }

                if (input.equals("list")) {
                    System.out.print(list);
                    continue;
                }
            }

            try {
                if (splitInput[0].equals("mark")) {
                    list.markTaskAsComplete(Integer.parseInt(splitInput[1]));
                    continue;
                } else if (splitInput[0].equals("unmark")) {
                    list.markTaskAsIncomplete(Integer.parseInt(splitInput[1]));
                    continue;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }


            Task task = new Task(input);
            list.addTask(task);
            System.out.printf("added: %s%n", input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
