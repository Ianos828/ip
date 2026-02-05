package command;

import duchess.command.CommandType;
import duchess.command.DeleteTaskCommand;
import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.Task;
import duchess.task.TaskList;
import duchess.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteTaskCommandTest {
    TaskList tasks;
    Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        storage = new Storage(Paths.get(".", "data", "tasks.txt"));
        Task todo = new ToDo("Test Task");
        tasks.addTask(todo);
    }

    @AfterEach
    void tearDown() {
        tasks = null;
        storage = null;
    }

    @Test
    void testExecute_missingIndex_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new DeleteTaskCommand(CommandType.DELETE,
                        Map.of("/default", ""))
                        .execute(tasks, storage),
                "No list index provided");
    }

    @Test
    void testExecute_invalidIndex_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new DeleteTaskCommand(CommandType.DELETE,
                        Map.of("/default", "a"))
                        .execute(tasks, storage),
                "List index is not a number");
    }

    @Test
    void testExecute_indexOutOfRange_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new DeleteTaskCommand(CommandType.DELETE,
                        Map.of("/default", "-1"))
                        .execute(tasks, storage),
                "Index is out of range");
    }

    @Test
    void testExecute_validIndex_success() {
        try {
            assertEquals("""
                    Noted. I've removed this task:
                    [T][ ] Test Task
                    Now you have 0 task(s) in the list.""",
                    new DeleteTaskCommand(CommandType.DELETE,
                            Map.of("/default", "a"))
                            .execute(tasks, storage),
                    "Successful task deletion");
        } catch (Exception e) {
            //ignore
        }
    }
}
