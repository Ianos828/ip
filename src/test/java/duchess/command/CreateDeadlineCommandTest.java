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

public class CreateDeadlineCommandTest {
    TaskList tasks;
    Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        storage = new Storage(
                Paths.get(".", "data", "tasks.txt"),
                Paths.get(".", "data", "cheer.txt"));
    }

    @AfterEach
    void tearDown() {
        tasks = null;
        storage = null;
    }

    @Test
    void testExecute_missingTaskName_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateDeadlineCommand(
                        Map.of("/default", "",
                                "/by", "2001-01-01"))
                        .execute(tasks, storage),
                "Deadline is missing name");
    }

    @Test
    void testExecute_missingEndDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateDeadlineCommand(
                        Map.of("/default", "a",
                                "/by", ""))
                        .execute(tasks, storage),
                "Deadline is missing end date");
    }

    @Test
    void testExecute_invalidEndDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new CreateDeadlineCommand(
                        Map.of("/default", "a",
                                "/by", "now"))
                        .execute(tasks, storage),
                "End date is in invalid format");
    }

    @Test
    void testExecute_validInputs_success() {
        try {
            assertEquals("""
                    Got it! I've added this task:
                    [D][ ] a (by: Mon, 01 Jan 2001)
                    Now you have 1 task(s) in the list.""",
                    new CreateDeadlineCommand(
                            Map.of("/default", "a",
                                    "/by", "2001-01-01"))
                            .execute(tasks, storage),
                    "Deadline task should be successfully created");
        } catch (Exception e) {
            //ignore
        }
    }
}
