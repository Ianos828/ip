package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CreateDeadlineCommandTest {
    TaskList tasks;
    Storage mockStorage;

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
        assertThrows(MissingArgumentException.class,
                () -> new CreateDeadlineCommand(
                        Map.of("/default", "",
                                "/by", "2001-01-01"))
                        .execute(tasks, mockStorage),
                "Deadline is missing name");
    }

    @Test
    public void testExecute_missingEndDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateDeadlineCommand(
                        Map.of("/default", "a",
                                "/by", ""))
                        .execute(tasks, mockStorage),
                "Deadline is missing end date");
    }

    @Test
    public void testExecute_invalidEndDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateDeadlineCommand(
                        Map.of("/default", "a",
                                "/by", "now"))
                        .execute(tasks, mockStorage),
                "End date is in invalid format");
    }

    @Test
    public void testExecute_validInputs_success() {
        try {
            assertEquals("""
                    Got it! I've added this task:
                    [D][ ] a (by: Mon, 01 Jan 2001)
                    Now you have 1 task(s) in the list.""",
                    new CreateDeadlineCommand(
                            Map.of("/default", "a",
                                    "/by", "2001-01-01"))
                            .execute(tasks, mockStorage),
                    "Deadline task should be successfully created");
        } catch (Exception e) {
            //ignore
        }
    }
}
