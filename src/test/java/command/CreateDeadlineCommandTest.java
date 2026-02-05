package command;

import duchess.command.CommandType;
import duchess.command.CreateDeadlineCommand;
import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateDeadlineCommandTest {
    TaskList tasks;
    Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        storage = new Storage(Paths.get(".", "data", "tasks.txt"));
    }

    @AfterEach
    void tearDown() {
        tasks = null;
        storage = null;
    }

    @Test
    void testExecute() {
        assertThrows(MissingArgumentException.class,
                () -> new CreateDeadlineCommand(CommandType.DEADLINE,
                        Map.of("/default", ""))
                .execute(tasks, storage),
                "execute() should throw MissingArgumentException");

        assertThrows(InvalidArgumentException.class,
                () -> new CreateDeadlineCommand(CommandType.DEADLINE,
                        Map.of("/default", "a",
                                "/by", "now"))
                .execute(tasks, storage),
                "execute() should throw InvalidArgumentException");

        try {
            assertEquals("""
                            Got it! I've added this task:
                            [D][ ] a (by: Mon, 01 Jan 2001)
                            Now you have 1 task(s) in the list.""",
                    new CreateDeadlineCommand(CommandType.DEADLINE,
                            Map.of("/default", "a",
                                    "/by", "2001-01-01"))
                            .execute(tasks, storage),
                    "Deadline task should be successfully created");
        } catch (Exception e) {
            //ignore
        }
    }
}
