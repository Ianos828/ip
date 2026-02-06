package duchess.storage;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.parser.FileParser;
import duchess.task.Task;
import duchess.task.TaskList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;


/**
 * Storage class for loading and saving tasks to a file.
 */
public class Storage {
    private final File file;

    /**
     * Constructor for Storage class.
     *
     * @param filePath the path to the file
     */
    public Storage(Path filePath) {
        this.file = filePath.toAbsolutePath()
                    .normalize()
                    .toFile();
    }

    /**
     * Loads tasks from a file.
     *
     * @return a list of tasks loaded from the file
     * @throws IOException when the file cannot be found or read
     */
    public TaskList loadTasksFromFile() throws IOException {
        String rawTask;
        TaskList tasks = new TaskList();

        BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));

        while ((rawTask = reader.readLine()) != null) {
            try {
                Task task = FileParser.getTask(rawTask);
                if (task != null) {
                    tasks.addTask(task);
                }
            } catch (InvalidArgumentException | MissingArgumentException e) {
                // Ignore invalid tasks
            }
        }

        return tasks;
    }

    /**
     * Saves tasks to a file.
     * @param tasks the tasks to save
     * @throws IOException when the file cannot be created or written to
     */
    public void saveTasksToFile(TaskList tasks) throws IOException{
        if (!file.exists()) {
            createFile();
        }

        String fileString = tasks.toSaveString();
        Files.writeString(Path.of(file.getPath()), fileString, StandardCharsets.UTF_8);
    }

    /**
     * Creates a file and its parent directory if it does not exist.
     *
     * @throws IOException if the file or parent directory cannot be created
     */
    private void createFile() throws IOException{
        boolean isFolderCreated = file.getParentFile().mkdirs();
        boolean isFileCreated;

        isFileCreated = file.createNewFile();

        if (!isFolderCreated) {
            throw new IOException("Failed to create folder for file.");
        }

        if (!isFileCreated) {
            throw new IOException("Failed to create file.");
        }
    }
}
