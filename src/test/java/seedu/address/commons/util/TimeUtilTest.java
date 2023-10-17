package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TimeUtilTest {
    @Test
    public void testParseDateString_withValidDateInputs() {
        List<String> validDateInputs = List.of(
            "2023-09-28",
            "20230928",
            "28 September 2023",
            "28 Sep 2023"
        );
        LocalDate expected = LocalDate.of(2023, 9, 28); // arbitrary test date
        for (String input : validDateInputs) {
            try {
                LocalDate actual = TimeUtil.parseDateString(input);
                assertEquals(expected, actual);
            } catch (NoSuchElementException e) {
                fail();
            }
        }
    }

    @Test
    public void testParseDateString_withInvalidDateInputs_shouldThrowNoSuchElementException() {
        List<String> invalidDateInputs = List.of(
            "", // empty
            "abc", // non-numeric
            "2023-13-25", // invalid month
            "2023-09-32", // invalid day
            "2021-02-29", // 29 Feb on a non-leap year.
            "2023090", // incomplete string
            "15 Febrary 2023" // mis-spelt month
        );
        for (String invalidInput : invalidDateInputs) {
            assertThrows(NoSuchElementException.class, () -> TimeUtil.parseDateString(invalidInput));
        }
    }

    @Test
    public void testIsValidDate_withValidDateInputs() {
        List<String> validDateInputs = List.of(
            "2023-09-28",
            "20230928",
            "28 September 2023",
            "28 Sep 2023"
        );
        for (String validInput : validDateInputs) {
            assertTrue(TimeUtil.isValidDate(validInput));
        }
    }

    @Test
    public void testIsValidDate_withInvalidInputs() {
        List<String> invalidDateInputs = List.of(
            "", // empty
            "abc", // non-numeric
            "2023-13-25", // invalid month
            "2023-09-32", // invalid day
            "2021-02-29", // 29 Feb on a non-leap year.
            "2023090", // incomplete string
            "15 Febrary 2023" // mis-spelt month
        );
        for (String invalidInput : invalidDateInputs) {
            assertFalse(TimeUtil.isValidDate(invalidInput));
        }
    }

    @Test
    public void testFindFormatter_withValidDateStringFormats() {
        List<String> validDateInputs = List.of(
            "2023-09-28", // ISO_LOCAL_DATE
            "20230928", // BASIC_ISO_DATE
            "28 Sep 2023", // "d MMM yyyy"
            "28 September 2023" // "d MMMM yyyy"
        );

        for (String validInput : validDateInputs) {
            Optional<DateTimeFormatter> optionalFormatter = TimeUtil.findFormatter(validInput);
            assertTrue(optionalFormatter.isPresent(), "Valid date string: \"" + validInput + "\" could not be parsed.");
        }
    }

    @Test
    public void testFindFormatter_withInvalidDateStringFormats() {
        List<String> invalidDateInputs = List.of(
            "", // empty
            "abc", // non-numeric
            "2023-13-25", // invalid month
            "2023-09-32", // invalid day
            "2021-02-29", // 29 Feb on a non-leap year.
            "2023090", // incomplete string
            "15 Febrary 2023" // mis-spelt month
        );
        for (String invalidInput : invalidDateInputs) {
            Optional<DateTimeFormatter> optionalFormatter = TimeUtil.findFormatter(invalidInput);
            assertFalse(optionalFormatter.isPresent());
        }
    }

    @Test
    public void testGetValidDateFormats_shouldReturnExampleFormatStrings() {
        // Call the method to test
        String formatsString = TimeUtil.getValidDateFormats();

        // Define the expected date strings. These should match the formats used in your TimeUtil class.
        String expectedDate1 = "'2023-10-15'"; // Corresponds to DateTimeFormatter.ISO_LOCAL_DATE
        String expectedDate2 = "'20231015'"; // Corresponds to DateTimeFormatter.BASIC_ISO_DATE
        String expectedDate3 = "'15 Oct 2023'"; // Corresponds to "d MMM yyyy"
        String expectedDate4 = "'15 October 2023'"; // Corresponds to "d MMMM yyyy"

        // Check that the formatsString contains all expected date strings
        assertTrue(formatsString.contains(expectedDate1), "Formats string should contain " + expectedDate1);
        assertTrue(formatsString.contains(expectedDate2), "Formats string should contain " + expectedDate2);
        assertTrue(formatsString.contains(expectedDate3), "Formats string should contain " + expectedDate3);
        assertTrue(formatsString.contains(expectedDate4), "Formats string should contain " + expectedDate4);
    }
}
