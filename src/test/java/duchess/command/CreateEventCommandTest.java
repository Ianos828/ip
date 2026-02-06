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

public class CreateEventCommandTest {
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
                () -> new CreateEventCommand(
                        Map.of("/default", ""))
                        .execute(tasks, mockStorage),
                "Event is missing name");
    }

    @Test
    public void testExecute_missingStartDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "",
                                "/to", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "Event is missing start date");
    }

    @Test
    public void testExecute_missingEndDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "2001-01-02",
                                "/to", ""))
                        .execute(tasks, mockStorage),
                "Event is missing end date");
    }

    @Test
    public void testExecute_invalidStartDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "now",
                                "/to", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "Start date is invalid");
    }

    @Test
    public void testExecute_invalidEndDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "2001-01-02",
                                "/to", "now"))
                        .execute(tasks, mockStorage),
                "End date is invalid");
    }

    @Test
    public void testExecute_endDateBeforeStartDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateEventCommand(
                        Map.of("/default", "a",
                                "/from", "2001-01-03",
                                "/to", "2001-01-02"))
                        .execute(tasks, mockStorage),
                "End date is before start date");
    }

    @Test
    public void testExecute_validInputs_success() {
        try {
            assertEquals("""
                    Got it! I've added this task:
                    [E][ ] a (from: Mon, 01 Jan 2001 to: Tue, 02 Jan 2001)
                    Now you have 1 task(s) in the list.""",
                    new CreateEventCommand(
                            Map.of("/default", "a",
                                    "/from", "2001-01-01",
                                    "/to", "2001-01-02"))
                            .execute(tasks, mockStorage),
                    "Event task should be successfully created");
        } catch (Exception e) {
            //ignore
        }
    }
}
