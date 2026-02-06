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
import static org.mockito.Mockito.mock;

public class MarkTaskIncompleteCommandTest {
    TaskList tasks;
    Storage mockStorage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        tasks.addTask(new ToDo("Test Task 1", true));
        mockStorage = mock(Storage.class);
    }

    @AfterEach
    public void tearDown() {
        tasks = null;
        mockStorage = null;
    }

    @Test
    public void testExecute_missingIndex_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new MarkTaskIncompleteCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "No list index provided");
    }
    @Test
    public void testExecute_invalidIndex_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new MarkTaskIncompleteCommand(
                        Map.of("/default", "-1"))
                        .execute(tasks, mockStorage),
                "Index is out of range");
    }

    @Test
    public void testExecute_validIndex_exceptionThrown() {
        try {
            assertEquals("""
                    OK, I've marked this task as not done yet:
                    [T][ ] Test Task 1""",
                    new MarkTaskIncompleteCommand(
                            Map.of("/default", "1"))
                            .execute(tasks, mockStorage),
                    "Marks the only task as undone");
        } catch (Exception e) {
            //ignore
        }

    }
}
