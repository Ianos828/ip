package duchess.parser;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileParserTest {
    @Test
    public void testGetTask_invalidCompletionMarker_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> FileParser.getTask("T | 2 | hello"),
                "Invalid completion marker");
    }

    @Test
    public void testGetTask_toDoMissingName_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> FileParser.getTask("T | 1"),
                "Missing task name");
    }

    @Test
    public void testGetTask_toDoTooManyArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> FileParser.getTask("T | 1 | hello | world"),
                "Invalid toDo task format");
    }

    @Test
    public void testGetTask_eventTooManyArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> FileParser.getTask("E | 1 | task | a | 2001-01-01 | 2001-01-02"),
                "Invalid event task format");
    }

    @Test
    public void testGetTask_deadlineTooManyArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> FileParser.getTask("D | 1 | hello | 2001-01-01 | 2001-01-01"),
                "Invalid deadline task format");
    }

    @Test
    public void testGetTask_eventTooLittleArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> FileParser.getTask("E | 1 | task"),
                "Invalid event task format");
    }

    @Test
    public void testGetTask_deadlineTooLittleArguments_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> FileParser.getTask("D | 1 | hello"),
                "Invalid deadline task format");
    }

    @Test
    public void testGetTask_invalidTaskType_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> FileParser.getTask("F | 1 | hello"),
                "Invalid task type");
    }

    @Test
    public void testGetTask_toDo_success() {
        String rawTask = "T | 0 | task";
        try {
            assertEquals(ToDo.class,
                    FileParser.getTask(rawTask)
                            .getClass(),
                    "ToDo object created successfully");
            assertEquals(false,
                    FileParser.getTask(rawTask)
                            .isComplete(),
                    "ToDo object initialised with correct completion status");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testGetTask_event_success() {
        String rawTask = "E | 1 | task | 2001-01-01 | 2001-01-02";
        try {
            assertEquals(Event.class,
                    FileParser.getTask(rawTask)
                            .getClass(),
                    "Event object created successfully");
            assertTrue(FileParser.getTask(rawTask).isComplete(),
                    "Event object initialised with correct completion status");
        } catch (Exception e) {
            //ignore
        }
    }

    @Test
    public void testGetTask_deadline_success() {
        String rawTask = "D | 1 | task | 2001-01-01";
        try {
            assertEquals(Deadline.class,
                    FileParser.getTask(rawTask)
                            .getClass(),
                    "Deadline object created successfully");
            assertTrue(FileParser.getTask(rawTask).isComplete(),
                    "Deadline object initialised with correct completion status");
        } catch (Exception e) {
            //ignore
        }
    }
}
