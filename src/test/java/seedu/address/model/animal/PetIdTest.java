package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PetIdTest {

    private static final String INVALID_EMPTY_INPUT = "";
    private static final String INVALID_WHITESPACE_INPUT = " ";
    private static final String INVALID_SYMBOL_INPUT = "#$%^&";
    private static final String INVALID_ALPHA_INPUT = "IdentificationNumber";
    private static final String INVALID_ALPHANUMERIC_INPUT = "IDis1234";
    private static final String INVALID_LONGER_NUMERIC_LENGTH_INPUT = "12345";
    private static final String INVALID_SHORTER_NUMERIC_LENGTH_INPUT = "12";
    private static final String VALID_NUMBER_INPUT = "1234";

    private static final PetId validPetId = new PetId(VALID_NUMBER_INPUT);

    @Test
    void testConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PetId(null));
    }

    @Test
    void testConstructor_invalidName_throwsIllegalArgumentException() {
        List<String> invalidPetIds = List.of(
                INVALID_EMPTY_INPUT,
                INVALID_WHITESPACE_INPUT,
                INVALID_SYMBOL_INPUT,
                INVALID_ALPHA_INPUT,
                INVALID_ALPHANUMERIC_INPUT,
                INVALID_LONGER_NUMERIC_LENGTH_INPUT,
                INVALID_SHORTER_NUMERIC_LENGTH_INPUT);
        for (String invalidPetId : invalidPetIds) {
            assertThrows(IllegalArgumentException.class, () -> new PetId(invalidPetId));
        }
    }

    @Test
    void testConstructor_valid_name() {
        assertDoesNotThrow(() -> new PetId(VALID_NUMBER_INPUT));
    }

    @Test
    void testIsValidName_withInvalidNames() {
        List<String> invalidPetIds = List.of(
                INVALID_EMPTY_INPUT,
                INVALID_WHITESPACE_INPUT,
                INVALID_SYMBOL_INPUT,
                INVALID_ALPHA_INPUT,
                INVALID_ALPHANUMERIC_INPUT,
                INVALID_LONGER_NUMERIC_LENGTH_INPUT,
                INVALID_SHORTER_NUMERIC_LENGTH_INPUT);
        for (String invalidPetId : invalidPetIds) {
            assertFalse(PetId.isValidPetId(invalidPetId));
        }
    }

    @Test
    void testIsValidName_withValidNames() {
        assertTrue(PetId.isValidPetId(VALID_NUMBER_INPUT));
    }
    @Test
    void testEquals_withSameValues() {
        assertEquals(validPetId, new PetId(VALID_NUMBER_INPUT));
    }

    @Test
    void testEquals_withSameObject() {
        assertEquals(validPetId, validPetId);
    }

    @Test
    void testEquals_withNull() {
        assertNotEquals(null, validPetId);
    }

    @Test
    void testEquals_withDifferentValues() {
        PetId diffPetId = new PetId("9999");
        assertNotEquals(validPetId, diffPetId);
    }

    @Test
    public void testHashCode() {
        assertEquals(validPetId, new PetId(VALID_NUMBER_INPUT));
    }

}
