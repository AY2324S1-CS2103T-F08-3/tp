package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Sex {
    public static final String MESSAGE_CONSTRAINTS = "Sex should be MALE | FEMALE";

    private final SexEnum sex;

    /**
     * Constructs a {@code Sex}.
     *
     * @param sex A valid sex.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        this.sex = SexEnum.valueOf(sex.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid Sex.
     */
    public static boolean isValidSex(String test) {
        return SexEnum.isValid(test);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sex sex1 = (Sex) o;
        return sex == sex1.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sex);
    }

    /**
     * Returns the short-form of the Sex.
     *
     * @return "M" if MALE, "F" if FEMALE.
     */
    @Override
    public String toString() {
        return sex.getShortForm();
    }
}
