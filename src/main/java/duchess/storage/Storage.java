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


public class Storage {
    private final File file;

    public Storage(Path filePath) {
        this.file = filePath.toAbsolutePath()
                    .normalize()
                    .toFile();
    }

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

    public void saveTasksToFile(TaskList tasks) throws IOException{
        if (!file.exists()) {
            createFile();
        }

        String fileString = tasks.toSaveString();
        Files.writeString(Path.of(file.getPath()), fileString, StandardCharsets.UTF_8);
    }

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
