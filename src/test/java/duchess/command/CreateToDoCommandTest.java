package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;

public class CreateToDoCommandTest {
    private TaskList tasks;
    private Storage mockStorage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        mockStorage = mock(Storage.class);
    }

    @AfterEach
    public void tearDown() {
        tasks = null;
        mockStorage = null;
    }

    @Test
    public void testExecute_missingTaskName_exceptionThrown() {
        assertThrows(MissingArgumentException.class, () ->
                new CreateToDoCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "Todo is missing name");
    }

    @Test
    public void testExecute_validInputs_success() {
        try {
            assertEquals("""
                    Got it! I've added this task:
                    [T][ ] a
                    Now you have 1 task(s) in the list.""",
                    new CreateToDoCommand(
                            Map.of("/default", "a"))
                            .execute(tasks, mockStorage),
                    "Todo task should be successfully created");
        } catch (Exception e) {
            //ignore
        }
    }
}
