package duchess;

import command.Command;
import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import parser.CommandParser;
import storage.TaskSaver;
import task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Duchess chatbot can manage tasks and their completion statuses.
 */
public class Duchess {
    private static final Path SAVE_FILE_PATH = Paths.get(".", "data", "tasks.txt");
    /**
     * Runs the chatbot.
     *
     * @param args optional startup arguments
     */
    public static void main(String[] args) {
        TaskSaver saver = new TaskSaver(SAVE_FILE_PATH);

        System.out.println("Hello! I'm Duchess!");
        System.out.println("What can I do for you?");

        boolean shouldTerminate = false;

        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();

        while (!shouldTerminate) {
            String input = scanner.nextLine();
            Command command = CommandParser.getCommand(input);

            try {
                command.execute(list, saver);
                shouldTerminate = command.isTerminatingCommand();
            } catch (InvalidArgumentException | MissingArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}