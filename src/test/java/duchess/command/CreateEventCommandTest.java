package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateEventCommandTest {
    TaskList tasks;
    Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        storage = new Storage(Paths.get(".", "data", "tasks.txt"));
    }

    @AfterEach
    void tearDown() {
        tasks = null;
        storage = null;
    }

    @Test
    void testExecute_missingTaskName_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateEventCommand(CommandType.EVENT,
                        Map.of("/default", ""))
                        .execute(tasks, storage),
                "Event is missing name");
    }

    @Test
    void testExecute_missingStartDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateEventCommand(CommandType.EVENT,
                        Map.of("/default", "a",
                                "/from", "",
                                "/to", "2001-01-02"))
                        .execute(tasks, storage),
                "Event is missing start date");
    }

    @Test
    void testExecute_missingEndDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateEventCommand(CommandType.EVENT,
                        Map.of("/default", "a",
                                "/from", "2001-01-02",
                                "/to", ""))
                        .execute(tasks, storage),
                "Event is missing end date");
    }

    @Test
    void testExecute_invalidStartDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateEventCommand(CommandType.EVENT,
                        Map.of("/default", "a",
                                "/from", "now",
                                "/to", "2001-01-02"))
                        .execute(tasks, storage),
                "Start date is invalid");
    }

    @Test
    void testExecute_invalidEndDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateEventCommand(CommandType.EVENT,
                        Map.of("/default", "a",
                                "/from", "2001-01-02",
                                "/to", "now"))
                        .execute(tasks, storage),
                "End date is invalid");
    }

    @Test
    void testExecute_endDateBeforeStartDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateEventCommand(CommandType.EVENT,
                        Map.of("/default", "a",
                                "/from", "2001-01-03",
                                "/to", "2001-01-02"))
                        .execute(tasks, storage),
                "End date is before start date");
    }

    @Test
    void testExecute_validInputs_success() {
        try {
            assertEquals("""
                    Got it! I've added this task:
                    [E][ ] a (from: Mon, 01 Jan 2001 to: Tue, 02 Jan 2001)
                    Now you have 1 task(s) in the list.""",
                    new CreateEventCommand(CommandType.EVENT,
                            Map.of("/default", "a",
                                    "/from", "2001-01-01",
                                    "/to", "2001-01-02"))
                            .execute(tasks, storage),
                    "Event task should be successfully created");
        } catch (Exception e) {
            //ignore
        }
    }
}
