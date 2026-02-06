package duchess.ui;

import java.util.Scanner;

/**
 * Ui class to handle user input and display messages.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return the user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to display
     */
    public void display(String message) {
        System.out.println(message);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void displayWelcomeMessage() {
        display("Hello! I'm Duchess!\nWhat can I do for you?");
    }

    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void displayLoadingErrorMessage() {
        display("Error loading tasks from file. Starting fresh...");
    }
}
