package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents an Animal's Breed in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBreed(String)}
 */
public class Breed {
    public static final String MESSAGE_CONSTRAINTS =
        "Breed names should only contain alphabetic characters and spaces "
            + "and should not have multiple consecutive spaces.";


    // This regex ensures the breed name is alphabetic, allows single spaces between words,
    // and disallows consecutive spaces.
    public static final String VALIDATION_REGEX = "^[A-Za-z]+( [A-Za-z]+)*$";

    private final String breedName;

    /**
     * Constructs a {@code Breed}.
     *
     * @param breedName A valid breed name.
     */
    public Breed(String breedName) {
        requireNonNull(breedName);
        checkArgument(isValidBreed(breedName), MESSAGE_CONSTRAINTS);
        this.breedName = breedName;
    }

    /**
     * Returns true if a given string is a valid breed name.
     */
    public static boolean isValidBreed(String test) {
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
        Breed breed = (Breed) o;
        return breedName.equals(breed.breedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breedName);
    }

    @Override
    public String toString() {
        return breedName;
    }
}
