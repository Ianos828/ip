package duchess.parser;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utility {
    /**
     * Returns the specified input as an array of length 2, after splitting with a specified string as the delimiter.
     *
     * @param input the input command string from the user
     * @return a String array of length 2
     */
    public static String[] splitIntoPair(String input, String delimiter) {
        String[] split = input.split(delimiter, 2);

        if (split.length == 1) {
            return new String[]{ split[0], "" };
        }

        split[1] = split[1].strip();
        return split;
    }

    public static boolean isInvalidString(String name) {
        return name == null || name.isEmpty();
    }

    /**
     * Returns an index to a list as an integer after extracting the argument from the delimiter-argument pair.
     *
     * @param indexAsString map containing a delimiter-argument pair
     * @return an integer denoting the list index
     * @throws MissingArgumentException if argument is empty string or null
     * @throws InvalidArgumentException if the argument is not a number or multiple numbers are specified
     */
    public static int parseInt(String indexAsString) throws MissingArgumentException, InvalidArgumentException {
        if (isInvalidString(indexAsString)) {
            throw new MissingArgumentException("No index provided!");
        }

        int index;

        try {
            index = Integer.parseInt(indexAsString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Index provided is not a single number!");
        }

        return index;
    }

    public static LocalDate parseDate(String dateAsString) throws MissingArgumentException, InvalidArgumentException {
        if (isInvalidString(dateAsString)) {
            throw new MissingArgumentException("No date provided! Expected format: YYYY-MM-DD");
        }

        LocalDate date;

        try {
            date = LocalDate.parse(dateAsString);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Date provided is not in the correct format! Expected format: YYYY-MM-DD");
        }

        return date;
    }

    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("EEE, dd LLL yyyy"));
    }
}
