package duchess.ui;

import duchess.command.Command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

import duchess.parser.CommandParser;

import duchess.storage.Storage;

import duchess.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * The Duchess chatbot can manage tasks and their completion statuses.
 */
public class Duchess {
    private static final Path SAVE_FILE_PATH = Paths.get(".", "data", "tasks.txt");
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duchess() {
        storage = new Storage(SAVE_FILE_PATH);
        ui = new Ui();

        try {
            tasks = storage.loadTasksFromFile();
        } catch (IOException e) {
            ui.displayLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean shouldTerminate = false;

        ui.displayWelcomeMessage();

        while (!shouldTerminate) {
            String input = ui.readCommand();
            Command command = CommandParser.getCommand(input);

            try {
                String commandOutput = command.execute(tasks, storage);
                ui.display(commandOutput);
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
     * Runs the chatbot.
     *
     * @param args optional startup arguments
     */
    public static void main(String[] args) {
        new Duchess().run();
    }
}