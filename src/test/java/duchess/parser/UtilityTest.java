package duchess.parser;

import duchess.exception.InvalidArgumentException;
import duchess.exception.MissingArgumentException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilityTest {
    @Test
    public void testSplitIntoPair_singleLengthArray_success() {
        assertEquals("",
                Utility.splitIntoPair("0", " ")[1],
                "Handle single-length array");
    }

    @Test
    public void testSplitIntoPair_emptyArray_success() {
        assertEquals("",
                Utility.splitIntoPair("", " ")[1],
                "Handle empty array");
    }

    @Test
    public void testSplitIntoPair_validInput_success() {
        assertEquals("world",
                Utility.splitIntoPair("hello | world", " \\| ")[1],
                "Split valid input");
    }

    @Test
    public void testParseInt_missingNumber_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> Utility.parseInt(""),
                "Missing number");
    }

    @Test
    public void testParseInt_invalidNumber_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> Utility.parseInt("hello"),
                "Input is not a number");
    }

    @Test
    public void testParseDate_missingDate_exceptionThrown() {
        assertThrows(MissingArgumentException.class,
                () -> Utility.parseDate(""),
                "Missing date");
    }

    @Test
    public void testParseDate_invalidDate_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
                () -> Utility.parseInt("hello"),
                "Input is not a date");
    }

    @Test
    public void testFormatDate() {
        assertEquals("Thu, 05 Feb 2026",
                Utility.formatDate(LocalDate.parse("2026-02-05")),
                "Format LocalDate into specific String format");
    }
}
