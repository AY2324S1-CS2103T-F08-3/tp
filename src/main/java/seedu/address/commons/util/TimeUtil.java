package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Provides utility functions to handle and format time-related inputs.
 * <p>
 * This utility class provides multiple date and time formats for parsing,
 * which can be helpful in accommodating various user inputs.
 * </p>
 */
public class TimeUtil {
    private static final DateTimeFormatter[] VALID_DATE_FORMATS = {
        DateTimeFormatter.ISO_LOCAL_DATE,
        DateTimeFormatter.BASIC_ISO_DATE,
        DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH),
        DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
    };

    // Private constructor to prevent instantiation
    private TimeUtil() {}

    /**
     * Parses the provided date-time string to a LocalDateTime object.
     * <p>
     * The function tries various date-time patterns to find a match.
     * If none of the patterns match, it throws an exception. This exception should
     * never be thrown in normal conditions as {@link TimeUtil#isValidDate(String)}
     * should always be called before parsing.
     * </p>
     *
     * @param dateString The date-time string to parse.
     * @return A LocalDateTime object.
     * @throws NoSuchElementException if the dateString is not a valid Date format.
     */
    public static LocalDate parseDateString(String dateString) {
        return findFormatter(dateString)
            .map(formatter -> LocalDate.parse(dateString, formatter))
            .orElseThrow();
    }

    /**
     * Checks whether a given string is a valid date representation.
     * <p>
     * This method tries to parse the input string using different date-time formats. If any of the formats
     * match, the method returns true, indicating that the string is a valid date.
     * </p>
     *
     * @param test The string to check for date validity.
     * @return true if the string is a valid date representation; false otherwise.
     */
    public static boolean isValidDate(String test) {
        return findFormatter(test).isPresent();
    }

    /**
     * Generates a string message detailing the valid date formats supported.
     * <p>
     * This method creates a formatted string containing examples of valid date representations. It is useful
     * for providing guidance to users on acceptable date input formats.
     * </p>
     *
     * @return A string containing examples of valid date formats.
     */
    public static String getValidDateFormats() {
        LocalDate sampleDate = LocalDate.of(2023, 10, 15); // an arbitrary date

        // Build a string showing the sample date in each format
        StringBuilder formatsBuilder = new StringBuilder();
        for (DateTimeFormatter formatter : VALID_DATE_FORMATS) {
            if (formatsBuilder.length() > 0) {
                formatsBuilder.append(System.lineSeparator());
            }
            // Append the sample date in the current format
            formatsBuilder.append("'").append(formatter.format(sampleDate)).append("'");
        }

        return formatsBuilder.toString();
    }

    /**
     * Attempts to identify a suitable {@link DateTimeFormatter} for a given date string.
     * <p>
     * This method iterates through a predefined set of {@link DateTimeFormatter} objects and attempts to parse
     * the provided date string. If parsing is successful, it returns the formatter used. If no formatter matches,
     * it returns an empty {@link Optional}.
     * </p>
     *
     * @param dateString The date string for which to find a suitable formatter.
     * @return An {@link Optional} containing the suitable {@link DateTimeFormatter} if found, or empty otherwise.
     */
    public static Optional<DateTimeFormatter> findFormatter(String dateString) {
        for (DateTimeFormatter formatter : VALID_DATE_FORMATS) {
            try {
                // Attempt to parse the date string. If successful, this is the correct formatter.
                LocalDate.parse(dateString, formatter);
                return Optional.of(formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try the next format.
            }
        }
        return Optional.empty(); // No suitable formatter found.
    }
}
