package utility;

public class Utility {
    /**
     * Returns the specified input as an array of length 2, after splitting with a specified string as the delimiter.
     *
     * @param input the input command string from the user
     * @return a String array of length 2
     */
    public static String[] splitIntoPair(String input, String regex) {
        String[] split = input.split(regex, 2);
        if (split.length == 1) {
            return new String[]{ split[0], "" };
        }
        split[1] = split[1].strip();
        return split;
    }

    public static boolean isNotValidName(String name) {
        return name == null || name.isEmpty();
    }
}
