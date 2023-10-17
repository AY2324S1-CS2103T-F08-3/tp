package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Species {
    public static final String MESSAGE_CONSTRAINTS =
        "Species names should only contain alphabetic characters and spaces "
            + "and should not have multiple consecutive spaces.";


    // This regex ensures the species name is alphabetic, allows single spaces between words,
    // and disallows consecutive spaces.
    public static final String VALIDATION_REGEX = "^[A-Za-z]+( [A-Za-z]+)*$";

    private final String speciesName;

    /**
     * Constructs a {@code Species}.
     *
     * @param speciesName A valid species name.
     */
    public Species(String speciesName) {
        requireNonNull(speciesName);
        checkArgument(isValidSpecies(speciesName), MESSAGE_CONSTRAINTS);
        this.speciesName = speciesName;
    }

    /**
     * Returns true if a given string is a valid breed name.
     */
    public static boolean isValidSpecies(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Species that = (Species) o;
        return speciesName.equals(that.speciesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciesName);
    }

    @Override
    public String toString() {
        return speciesName;
    }
}
