package duchess.parser;

import duchess.command.CreateDeadlineCommand;
import duchess.command.CreateEventCommand;
import duchess.command.CreateToDoCommand;
import duchess.command.DeleteTaskCommand;
import duchess.command.DisplayListCommand;
import duchess.command.FindOutstandingCommand;
import duchess.command.MarkTaskCompleteCommand;
import duchess.command.MarkTaskIncompleteCommand;
import duchess.command.TerminateCommand;
import duchess.command.UnknownCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    @Test
    public void testGetCommand() {
        assertEquals(CreateDeadlineCommand.class, CommandParser.getCommand("deadline").getClass());
        assertEquals(CreateEventCommand.class, CommandParser.getCommand("event").getClass());
        assertEquals(CreateToDoCommand.class, CommandParser.getCommand("todo").getClass());

        assertEquals(DeleteTaskCommand.class, CommandParser.getCommand("delete").getClass());
        assertEquals(DisplayListCommand.class, CommandParser.getCommand("list").getClass());
        assertEquals(FindOutstandingCommand.class, CommandParser.getCommand("outstanding").getClass());

        assertEquals(MarkTaskCompleteCommand.class, CommandParser.getCommand("mark").getClass());
        assertEquals(MarkTaskIncompleteCommand.class, CommandParser.getCommand("unmark").getClass());

        assertEquals(TerminateCommand.class, CommandParser.getCommand("bye").getClass());

        assertEquals(UnknownCommand.class, CommandParser.getCommand("").getClass());
        assertEquals(UnknownCommand.class, CommandParser.getCommand("hello").getClass());
    }
}
