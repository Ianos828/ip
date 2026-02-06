package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.task.ToDo;
import duchess.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class DisplayListCommandTest {
    Storage mockStorage;

    @BeforeEach
    public void setUp() {
        mockStorage = mock(Storage.class);
    }

    @AfterEach
    public void tearDown() {
        mockStorage = null;
    }

    @Test
    public void testExecute_emptyList_success() {
        assertEquals("Your list is empty!",
                new DisplayListCommand()
                        .execute(new TaskList(), mockStorage),
                "List is empty");
    }

    @Test
    public void testExecute_nonEmptyList_success() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Test Task"));
        tasks.addTask(new ToDo("Test Task 2"));

        assertEquals("""
                Here are the tasks in your list:
                1. [T][ ] Test Task
                2. [T][ ] Test Task 2""",
                new DisplayListCommand()
                        .execute(tasks, mockStorage),
                "2 tasks in list should be displayed");
    }
}
