package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkTaskCompleteCommandTest {
    TaskList tasks;
    Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        tasks.addTask(new ToDo("Test Task 1"));
        storage = new Storage(Paths.get(".", "data", "tasks.txt"));
    }

    @AfterEach
    public void tearDown() {
        tasks = null;
        storage = null;
    }

    @Test
    public void testExecute_missingIndex_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new MarkTaskCompleteCommand(
                        Map.of("/default", ""))
                        .execute(tasks, storage),
                "No list index provided");
    }
    @Test
    public void testExecute_invalidIndex_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new MarkTaskCompleteCommand(
                        Map.of("/default", "-1"))
                        .execute(tasks, storage),
                "Index is out of range");
    }

    @Test
    public void testExecute_validIndex_exceptionThrown() {
        try {
            assertEquals("""
                    Nice! I've marked this task as done:
                    [T][X] Test Task 1""",
                    new MarkTaskCompleteCommand(
                            Map.of("/default", "1"))
                            .execute(tasks, storage),
                    "Marks the only task as done");
        } catch (Exception e) {
            //ignore
        }

    }
}
