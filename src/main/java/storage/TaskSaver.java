package storage;

import task.Task;
import task.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TaskSaver {
    private final File file;

    public TaskSaver(Path filePath) {
        this.file = filePath.toAbsolutePath()
                    .normalize()
                    .toFile();
    }
    public void saveTasksToFile(TaskList taskList) {
        try {
            if (!file.exists()) {
                createFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            StringBuilder fileString = new StringBuilder();
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
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
