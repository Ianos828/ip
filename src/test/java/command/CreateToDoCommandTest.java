package command;

import duchess.command.CommandType;
import duchess.command.CreateToDoCommand;
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

public class CreateToDoCommandTest {
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
                () -> new CreateToDoCommand(CommandType.TODO,
                        Map.of("/default", ""))
                        .execute(tasks, storage),
                "Todo is missing name");
    }

    @Test
    void testExecute_validInputs_success() {
        try {
            assertEquals("""
                    Got it! I've added this task:
                    [T][ ] a
                    Now you have 1 task(s) in the list.""",
                    new CreateToDoCommand(CommandType.TODO,
                        Map.of("/default", "a"))
                        .execute(tasks, storage),
                "Todo task should be successfully created");
        } catch (Exception e) {
            //ignore
        }
    }
}
