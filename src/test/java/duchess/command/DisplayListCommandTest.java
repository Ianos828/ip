package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisplayListCommandTest {
    Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage(Paths.get(".", "data", "tasks.txt"));
    }

    @AfterEach
    void tearDown() {
        storage = null;
    }

    @Test
    public void testExecute_emptyList_success() {
        assertEquals("Your list is empty!",
                new DisplayListCommand()
                        .execute(new TaskList(), storage),
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
                        .execute(tasks, storage),
                "2 tasks in list should be displayed");
    }
}
