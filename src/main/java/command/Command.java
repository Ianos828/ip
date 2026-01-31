package command;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import task.TaskList;

/**
 * Class representing a generic user command.
 */
public abstract class Command{
    CommandType type;

    /**
     * Constructor for Command class.
     *
     * @param type the command type
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Returns true if program should end the main program, else false.
     *
     * @return boolean representing if the command should terminate the main program
     */
    public boolean isTerminatingCommand() {
        return false;
    }

    /**
     * Abstract generic execute method for all commands to complete their specified actions.
     *
     * @param list list of tasks that commands will operate on
     * @throws MissingArgumentException if commands do not receive their expected number of arguments
     * @throws InvalidArgumentException if commands do not receive their expected arguments in the correct format
     */
    public abstract void execute(TaskList list) throws MissingArgumentException, InvalidArgumentException;
}


