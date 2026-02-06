package duchess.command;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import duchess.storage.Storage;
import duchess.task.TaskList;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class DisplayQuoteCommand extends Command{
    Random random;

    /**
     * Constructor for DisplayQuoteCommand class.
     */
    public DisplayQuoteCommand(){
        random = new Random();
    }

    /**
     * Displays a random quote from the list of quotes.
     * @param tasks list of tasks that commands will operate on
     * @param storage storage for saving and loading task lists
     * @return quote to be displayed to the user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<String> quotes = storage.getQuotes();

        if (quotes.isEmpty()) {
            return "There are no quotes available!";
        }

        int randomIndex = random.nextInt(quotes.size());
        return quotes.get(randomIndex);
    }
}
