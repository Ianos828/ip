package duchess.ui;

import duchess.command.Command;
import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.parser.CommandParser;
import duchess.storage.Storage;
import duchess.task.TaskList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;

/**
 * The Duchess chatbot can manage tasks and their completion statuses.
 *
 * <p>
 * The chatbot supports loading and saving tasks to a file.
 * </p>
 */
public class Duchess {
    private static final Path SAVE_FILE_PATH = Paths.get(".", "data", "tasks.txt");
    private static final Path QUOTES_FILE_PATH = Paths.get(".", "data", "cheer.txt");
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duchess class.
     * <p>
     * Tries to load tasks and quotes from files.
     * If loading fails, creates a new TaskList and empty list of quotes.
     * </p>
     */
    public Duchess() {
        storage = new Storage(SAVE_FILE_PATH, QUOTES_FILE_PATH);
        ui = new Ui();

        try {
            tasks = storage.loadTasksFromFile();
        } catch (IOException e) {
            ui.displayLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        boolean shouldTerminate = false;

        ui.displayWelcomeMessage();

        while (!shouldTerminate) {
            String input = ui.readCommand();
            Command command = CommandParser.getCommand(input);

            try {
                String result = command.execute(tasks, storage);
                ui.display(result);
                shouldTerminate = command.isTerminatingCommand();
            } catch (InvalidArgumentException | MissingArgumentException e) {
                ui.display(e.getMessage());
            } catch (Exception e) {
                ui.display(e.getMessage());
                ui.display(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    /**
     * main method for Duchess.
     *
     * @param args optional startup arguments
     */
    public static void main(String[] args) {
        new Duchess().run();
    }
}