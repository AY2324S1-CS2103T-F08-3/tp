package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SexTest {

    private static final String INVALID_EMPTY_INPUT = "";
    private static final String INVALID_WHITESPACE_INPUT = " ";
    private static final String INVALID_SYMBOL_INPUT = "#$%^&";
    private static final String INVALID_ALPHA_INPUT = "IdentificationNumber";
    private static final String INVALID_ALPHANUMERIC_INPUT = "IDis1234";
    private static final String VALID_SEX_MALE_INPUT = "MALE";
    private static final String VALID_SEX_FEMALE_INPUT = "FEMALE";

    private static final Sex validSex = new Sex(VALID_SEX_MALE_INPUT);

    @Test
    void testConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sex(null));
    }

    @Test
    void testConstructor_invalidSex_throwsIllegalArgumentException() {
        List<String> invalidSexes = List.of(
                INVALID_EMPTY_INPUT,
                INVALID_WHITESPACE_INPUT,
                INVALID_SYMBOL_INPUT,
                INVALID_ALPHA_INPUT,
                INVALID_ALPHANUMERIC_INPUT);
        for (String invalidSex : invalidSexes) {
            assertThrows(IllegalArgumentException.class, () -> new Sex(invalidSex));
        }
    }

    @Test
    void testConstructor_valid_sex() {
        assertDoesNotThrow(() -> new Sex(VALID_SEX_MALE_INPUT));
        assertDoesNotThrow(() -> new Sex(VALID_SEX_FEMALE_INPUT));
    }

    @Test
    void testIsValidSex_withInvalidSexes() {
        List<String> invalidSexes = List.of(
                INVALID_EMPTY_INPUT,
                INVALID_WHITESPACE_INPUT,
                INVALID_SYMBOL_INPUT,
                INVALID_ALPHA_INPUT,
                INVALID_ALPHANUMERIC_INPUT);
        for (String invalidSex : invalidSexes) {
            assertFalse(Sex.isValidSex(invalidSex));
        }
    }

    @Test
    void testIsValidSex_withValidSexes() {
        assertTrue(Sex.isValidSex(VALID_SEX_MALE_INPUT));
        assertTrue(Sex.isValidSex(VALID_SEX_FEMALE_INPUT));
    }
    @Test
    void testEquals_withSameValues() {
        assertEquals(validSex, new Sex(VALID_SEX_MALE_INPUT));
    }

    @Test
    void testEquals_withSameObject() {
        assertEquals(validSex, validSex);
    }

    @Test
    void testEquals_withNull() {
        assertNotEquals(validSex, null);
    }

    @Test
    void testEquals_withDifferentValues() {
        Sex diffSex = new Sex(VALID_SEX_FEMALE_INPUT);
        assertNotEquals(validSex, diffSex);
    }

    @Test
    public void testHashCode() {
        assertEquals(validSex.hashCode(), new Sex(VALID_SEX_MALE_INPUT).hashCode());
    }

}
