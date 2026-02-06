package duchess.command;

import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.TaskList;
import duchess.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindTaskCommandTest {
    TaskList tasks;
    Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new Storage(Paths.get(".", "data", "tasks.txt"));

        tasks.addTask(new ToDo("Test Task 1"));
        tasks.addTask(new Deadline("Test Task 2",
                LocalDate.parse("2001-01-01")));
        tasks.addTask(new Event("Test Task 3",
                LocalDate.parse("2001-01-03"),
                LocalDate.parse("2001-01-05")));
    }

    @AfterEach
    public void tearDown() {
        tasks = null;
        storage = null;
    }

    @Test
    public void testExecute_validKeyword_success() {
        try {
            assertEquals("""
                Here are the matching tasks in your list:
                1. [T][ ] Test Task 1
                2. [D][ ] Test Task 2 (by: Mon, 01 Jan 2001)
                3. [E][ ] Test Task 3 (from: Wed, 03 Jan 2001 to: Fri, 05 Jan 2001)""",
                new FindTaskCommand(Map.of("/default", "test")).execute(tasks, storage),
                "All tasks should match search keyword. ignoring case");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testExecute_validKeywordNoMatch_success() {
        try {
            assertEquals("No matching tasks found!",
                    new FindTaskCommand(Map.of("/default", "q")).execute(tasks, storage),
                    "No tasks should match search keyword");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testExecute_missingKeyword_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> new FindTaskCommand(Map.of("/default", ""))
                        .execute(tasks, storage),
                "Invalid keyword");
    }
}
