package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerminateCommandTest {
    TerminateCommand command;
    TaskList tasks;
    Storage storage;

    @BeforeEach
    public void setUp() {
        command = new TerminateCommand(CommandType.BYE);
        tasks = new TaskList();
        storage = new Storage(Paths.get(".", "data", "tasks.txt"));
    }

    @AfterEach
    public void tearDown() {
        command = null;
        tasks = null;
        storage = null;
    }

    @Test
    public void testExecute() {
        assertEquals("Bye. Hope to see you again soon!",
                command.execute(tasks, storage),
                "Ends the program");
    }
}
