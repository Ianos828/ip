package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DisplayQuoteCommandTest {
    TaskList tasks;
    Storage mockStorage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        mockStorage = mock(Storage.class);
    }

    @AfterEach
    public void tearDown() {
        tasks = null;
        mockStorage = null;
    }

    @Test
    public void testExecute_emptyList_success() {
        when(mockStorage.getQuotes()).thenReturn(new ArrayList<>());
        assertEquals("There are no quotes available!",
                new DisplayQuoteCommand()
                        .execute(tasks, mockStorage),
                "No quotes available");
    }

    @Test
    public void testExecute_singleQuote_success() {
        when(mockStorage.getQuotes()).thenReturn(List.of("Test Quote"));
        assertEquals("Test Quote",
                new DisplayQuoteCommand()
                        .execute(tasks, mockStorage),
                "Print the only quote available");
    }
}
