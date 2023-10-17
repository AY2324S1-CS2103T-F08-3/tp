package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

import seedu.address.commons.util.TimeUtil;

public class DateOfBirth {
    public static final String MESSAGE_CONSTRAINTS = String.format(
        "Date Of Birth should be in one of the following formats:%n%s", TimeUtil.getValidDateFormats());
    private final LocalDate dateOfBirth;

    /**
     * Constructs a {@code Age}.
     *
     * @param date a valid date.
     */
    public DateOfBirth(String date) {
        requireNonNull(date);
        checkArgument(TimeUtil.isValidDate(date), MESSAGE_CONSTRAINTS);
        this.dateOfBirth = TimeUtil.parseDateString(date);
    }

    public int getAge() {
        // Current date in Singapore time zone
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Singapore"));

        // Calculate the age as the difference in years between the current date and the dateOfBirth
        int age = currentDate.getYear() - dateOfBirth.getYear();

        // Adjust if today's date is before the user's birthday this year
        if (currentDate.getMonthValue() < dateOfBirth.getMonthValue() ||
            (currentDate.getMonthValue() == dateOfBirth.getMonthValue()
                && currentDate.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
            age--;
        }

        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DateOfBirth that = (DateOfBirth) o;
        return Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth);
    }

    @Override
    public String toString() {
        return String.valueOf(getAge());
    }
}
