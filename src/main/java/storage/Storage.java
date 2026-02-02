package storage;

import exception.MangledTaskException;
import parser.FileParser;
import task.Task;
import task.TaskList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private final File file;

    public Storage(Path filePath) {
        this.file = filePath.toAbsolutePath()
                    .normalize()
                    .toFile();
    }

    public TaskList loadTasksFromFile() {
        String rawTask;
        TaskList tasks = new TaskList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));

            while ((rawTask = reader.readLine()) != null) {
                try {
                    Task task = FileParser.getTask(rawTask);
                    if (task != null) {
                        tasks.addTask(task);
                    }
                } catch (MangledTaskException e) {
                    System.out.println(e.getMessage());
                    System.out.printf("Failed to parse task: %s. Skipping...%n", e.getRawTask());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return tasks;
    }

    public void saveTasksToFile(TaskList tasks) {
        try {
            if (!file.exists()) {
                createFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            StringBuilder fileString = new StringBuilder();
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                fileString.append(task.toSaveString()).append("\n");
            }
            Files.writeString(Path.of(file.getPath()), fileString, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    private void createFile() throws IOException{
        boolean isFolderCreated = file.getParentFile().mkdirs();
        boolean isFileCreated = file.createNewFile();

        if (!isFolderCreated) {
            throw new IOException("Failed to create folder for file.");
        }

        if (!isFileCreated) {
            throw new IOException("Failed to create file.");
        }
    }
}
