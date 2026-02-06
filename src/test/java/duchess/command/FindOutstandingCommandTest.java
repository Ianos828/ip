package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.storage.Storage;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.TaskList;
import duchess.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class FindOutstandingCommandTest {
    TaskList tasks;
    Storage mockStorage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        mockStorage = mock(Storage.class);

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
        mockStorage = null;
    }

    @Test
    public void testExecute_invalidDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> new FindOutstandingCommand(
                        Map.of("/default", "a"))
                        .execute(tasks, mockStorage),
                "Invalid date");
    }

    @Test
    public void testExecute_validDateJan2nd2001_success() {
        try {
            assertEquals("""
                    There are no outstanding tasks after Tue, 02 Jan 2001!""",
                    new FindOutstandingCommand(
                            Map.of("/default", "2001-01-02"))
                            .execute(tasks, mockStorage),
                    "No tasks match the criteria");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testExecute_validDateJan4th2001_success() {
        try {
            assertEquals("""
                    Here are the outstanding tasks in your list:
                    1. [E][ ] Test Task 3 (from: Wed, 03 Jan 2001 to: Fri, 05 Jan 2001)""",
                    new FindOutstandingCommand(
                            Map.of("/default", "2001-01-04"))
                            .execute(tasks, mockStorage),
                    "Only event gets filtered out");
        } catch (Exception e) {
            //ignore
        }
    }
}
