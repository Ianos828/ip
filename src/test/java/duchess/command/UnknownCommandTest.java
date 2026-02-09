package duchess.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duchess.storage.Storage;
import duchess.task.TaskList;

public class UnknownCommandTest {
    private UnknownCommand command;
    private TaskList tasks;
    private Storage mockStorage;

    @BeforeEach
    public void setUp() {
        command = new UnknownCommand();
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
        assertEquals("I'm sorry, but I don't know what that means :(",
                command.execute(tasks, mockStorage),
                "Prints an error message");
    }
}
