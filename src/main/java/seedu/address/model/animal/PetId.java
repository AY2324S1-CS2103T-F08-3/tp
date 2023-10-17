package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Pet's ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPetId(String)}
 */
public class PetId {
    public static final String MESSAGE_CONSTRAINTS =
        "Pet ID should only contain numbers, and it should be exactly 4 digits long";
    public static final String VALIDATION_REGEX = "\\d{4}";
    private final String petId;

    /**
     * Constructs a {@code PetId}.
     *
     * @param petId A valid pet ID.
     */
    public PetId(String petId) {
        requireNonNull(petId);
        checkArgument(isValidPetId(petId), MESSAGE_CONSTRAINTS);
        this.petId = petId;
    }

    /**
     * Returns true if a given petId is a valid PetId.
     */
    private static boolean isValidPetId(String test) {
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
        PetId petId1 = (PetId) o;
        return Objects.equals(petId, petId1.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }

    @Override
    public String toString() {
        return petId;
    }
}
