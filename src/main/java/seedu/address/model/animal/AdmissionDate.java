package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.TimeUtil;

public class AdmissionDate {
    public static final String MESSAGE_CONSTRAINTS = String.format(
        "Admission Date should be in one of the following formats:%n%s", TimeUtil.getValidDateFormats());

    private final LocalDate admissionDate;

    /**
     * Constructs a {@code AdmissionDate}.
     *
     * @param date a valid date.
     */
    public AdmissionDate(String date) {
        requireNonNull(date);
        checkArgument(TimeUtil.isValidDate(date), MESSAGE_CONSTRAINTS);
        this.admissionDate = TimeUtil.parseDateString(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdmissionDate that = (AdmissionDate) o;
        return Objects.equals(admissionDate, that.admissionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admissionDate);
    }

    @Override
    public String toString() {
        return admissionDate.toString();
    }
}
