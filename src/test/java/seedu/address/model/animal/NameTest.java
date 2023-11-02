package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class NameTest {

    private static final String INVALID_EMPTY_INPUT = "";
    private static final String INVALID_WHITESPACE_INPUT = " ";
    private static final String INVALID_SYMBOL_ONLY_INPUT = "#$%^&";

    private static final String VALID_ALPHA_INPUT = "JASON";
    private static final String VALID_ALPHANUMERICAL_INPUT = "Chang00se";
    private static final String VALID_ALPHANUMERICAL_WHITESPACE_INPUT = "Chang the G00se";

    private static final Name validName = new Name("Pookie");

    @Test
    void testConstructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    void testConstructor_invalidName_throwsIllegalArgumentException() {
        List<String> invalidNames = List.of(
                INVALID_EMPTY_INPUT,
                INVALID_WHITESPACE_INPUT,
                INVALID_SYMBOL_ONLY_INPUT);
        for (String invalidName : invalidNames) {
            assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
        }
    }

    @Test
    void testConstructor_valid_name() {
        List<String> validNames = List.of(
                VALID_ALPHA_INPUT,
                VALID_ALPHANUMERICAL_INPUT,
                VALID_ALPHANUMERICAL_WHITESPACE_INPUT
        );
        for (String validName : validNames) {
            assertDoesNotThrow(() -> new Name(validName));
        }
    }

    @Test
    void testIsValidName_withInvalidNames() {
        List<String> invalidNames = List.of(
                INVALID_EMPTY_INPUT,
                INVALID_WHITESPACE_INPUT,
                INVALID_SYMBOL_ONLY_INPUT);
        for (String invalidName : invalidNames) {
            assertFalse(Name.isValidName(invalidName));
        }
    }

    @Test
    void testIsValidName_withValidNames() {
        List<String> validNames = List.of(
                VALID_ALPHA_INPUT,
                VALID_ALPHANUMERICAL_INPUT,
                VALID_ALPHANUMERICAL_WHITESPACE_INPUT
        );
        for (String validName : validNames) {
            assertTrue(Name.isValidName(validName));
        }
    }
    @Test
    void testEquals_withSameValues() {
        assertEquals(validName, new Name("Pookie"));
    }

    @Test
    void testEquals_withSameObject() {
        assertEquals(validName, validName);
    }

    @Test
    void testEquals_withNull() {
        assertNotEquals(validName, null);
    }

    @Test
    void testEquals_withDifferentValues() {
        Name diffName = new Name("Bear");
        assertNotEquals(validName, diffName);
    }

    @Test
    public void testHashCode() {
        assertEquals(validName.hashCode(), new Name("Pookie").hashCode());
    }

}
