package duchess.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
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
    }

    @Test
    public void testAddTask() {
        tasks.addTask(new ToDo("Test Task 4"));
        assertEquals(4, tasks.getSize(), "1 more task is added to the list");
    }

    @Test
    public void testRemoveTask() {
        try {
            tasks.removeTask(0);
            assertEquals(2, tasks.getSize(), "1 task is removed from the list");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testMarkTaskAsComplete() {
        try {
            tasks.markTaskAsComplete(0);
            assertTrue(tasks.removeTask(0).isComplete(), "Removed task should be completed");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testMarkTaskAsIncomplete() {
        try {
            tasks.markTaskAsIncomplete(0);
            assertFalse(tasks.removeTask(0).isComplete(), "Removed task should be completed");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testGetOutstandingTasks() {
        assertEquals("""
                1. [E][ ] Test Task 3 (from: Wed, 03 Jan 2001 to: Fri, 05 Jan 2001)""",
                tasks.getOutstandingTasks(LocalDate.parse("2001-01-04"))
                        .toString(),
                "Only event is outstanding");
    }
}
