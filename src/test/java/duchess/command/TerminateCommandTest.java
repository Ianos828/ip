package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TerminateCommandTest {
    TerminateCommand command;
    TaskList tasks;
    Storage mockStorage;

    @BeforeEach
    public void setUp() {
        command = new TerminateCommand();
        tasks = new TaskList();
        mockStorage = mock(Storage.class);
    }

    @AfterEach
    public void tearDown() {
        command = null;
        tasks = null;
        mockStorage = null;
    }

    @Test
    public void testExecute() {
        assertEquals("Bye. Hope to see you again soon!",
                command.execute(tasks, mockStorage),
                "Ends the program");
    }
}
