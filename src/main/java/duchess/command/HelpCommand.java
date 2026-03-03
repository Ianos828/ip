package duchess.command;

import duchess.storage.Storage;
import duchess.task.TaskList;

/**
 * Class representing a command to display help information.
 */
public class HelpCommand extends Command{
    public String execute(TaskList tasks, Storage storage) {
        return """
                Hark, attend to the commands at thy disposal:
                
                1. todo NAME
                - doth forge a task.
                
                2. deadline NAME /by DATE
                - doth set a task with a term.
                
                3. event NAME /from START_DATE /to END_DATE
                - doth frame a task 'twixt two suns.
                
                4. delete INDEX
                - doth strike down a task by its given count.
                
                5. list
                - doth show forth all tasks in thy ledger.
                
                6. cheer
                - doth speak a random word of courage.
                
                7. outstanding DATE
                - doth reveal tasks yet to be done by the set day.
                
                8. find KEYWORD
                - doth seek tasks akin to the given word.
                
                9. mark INDEX
                - doth deem a task done by its count.
                
                10. unmark INDEX
                - doth call back a task to be done.
                
                11. bye
                - doth bid farewell to this program.
                
                For deeper lore, seek the README.md.""";
    }
}
