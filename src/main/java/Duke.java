import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                printList(list);
                continue;
            }

            list.add(input);
            System.out.printf("added: %s%n", input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(List<String> list) {
        int itemIndex = 1;
        for (String item : list) {
            System.out.printf("%d. %s%n", itemIndex, item);
            itemIndex++;
        }
    }
}
