package seedu.address.model.animal.exceptions;

/**
 * Signals that the operation will result in duplicate Animals (Animals are considered duplicates if they have the same
 * identity).
 */
public class DuplicateAnimalException extends RuntimeException {
    public DuplicateAnimalException() {
        super("Operation would result in duplicate animals");
    }
}


