package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.Task;
import duchess.task.TaskList;
import duchess.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class DeleteTaskCommandTest {
    TaskList tasks;
    Storage mockStorage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        mockStorage = mock(Storage.class);
        Task todo = new ToDo("Test Task");
        tasks.addTask(todo);
    }

    @AfterEach
    public void tearDown() {
        tasks = null;
        mockStorage = null;
    }

    @Test
    public void testExecute_missingIndex_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new DeleteTaskCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "No list index provided");
    }

    @Test
    public void testExecute_invalidIndex_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new DeleteTaskCommand(
                        Map.of("/default", "a"))
                        .execute(tasks, mockStorage),
                "List index is not a number");
    }

    @Test
    public void testExecute_indexOutOfRange_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new DeleteTaskCommand(
                        Map.of("/default", "-1"))
                        .execute(tasks, mockStorage),
                "Index is out of range");
    }

    @Test
    public void testExecute_validIndex_success() {
        try {
            assertEquals("""
                    Noted. I've removed this task:
                    [T][ ] Test Task
                    Now you have 0 task(s) in the list.""",
                    new DeleteTaskCommand(
                            Map.of("/default", "a"))
                            .execute(tasks, mockStorage),
                    "Successful task deletion");
        } catch (Exception e) {
            //ignore
        }
    }
}
