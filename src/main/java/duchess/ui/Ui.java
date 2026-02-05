package duchess.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void displayWelcomeMessage() {
        display("Hello! I'm Duchess!\nWhat can I do for you?");
    }

    public void displayLoadingErrorMessage() {
        display("Error loading tasks from file. Starting fresh...");
    }
}
