package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private static final String TEST_DESCRIPTION = "Walk Milo around the park";
    private static final Task incompleteTask = new Task(TEST_DESCRIPTION);
    private static final Task completeTask = new Task(TEST_DESCRIPTION, true);

    private static final String COMPLETED_STATUS_ICON = "X";
    private static final String INCOMPLETE_STATUS_ICON = " ";

    private static final String ALPHA_NUMERIC = "a1234";

    private static final String ALPHA_WHITESPACE = "This is a task";

    private static final String ALPHA_NUMERIC_WHITESPACE = "G0 f0r run";

    private static final String ALPHA_NUMERIC_SYMBOL_WHITESPACE = "Feed *$&%(@ sausage 2";

    @Test
    public void testConstructor_withValidInputs() {
        assertDoesNotThrow(() -> new Task(ALPHA_NUMERIC));
        assertDoesNotThrow(() -> new Task(ALPHA_WHITESPACE));
        assertDoesNotThrow(() -> new Task(ALPHA_NUMERIC_WHITESPACE));
        assertDoesNotThrow(() -> new Task(ALPHA_NUMERIC_SYMBOL_WHITESPACE));
    }

    @Test
    public void testConstructor_withNullInput() {
        assertThrows(NullPointerException.class, () -> new Task(null));
    }

    @Test
    public void testConstructor_withInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Task("")); //empty
        assertThrows(IllegalArgumentException.class, () -> new Task(" ")); //whitespace only
    }

    @Test
    public void testJacksonConstructor_withValidInputs() {
        assertDoesNotThrow(() -> new Task(ALPHA_NUMERIC, false));
        assertDoesNotThrow(() -> new Task(ALPHA_WHITESPACE, false));
        assertDoesNotThrow(() -> new Task(ALPHA_NUMERIC_WHITESPACE, true));
        assertDoesNotThrow(() -> new Task(ALPHA_NUMERIC_SYMBOL_WHITESPACE, true));
    }

    @Test
    public void testJacksonConstructor_withNullInput() {
        assertThrows(NullPointerException.class, () -> new Task(null, false));
    }

    @Test
    public void testJacksonConstructor_withInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Task("", true)); //empty
        assertThrows(IllegalArgumentException.class, () -> new Task(" ", true)); //whitespace only
    }

    @Test
    public void testGetDescription() {
        assertEquals(TEST_DESCRIPTION, incompleteTask.getDescription());
    }

    @Test
    public void testCheckIsDone() {
        assertFalse(incompleteTask.checkIsDone());
        assertTrue(completeTask.checkIsDone());
    }

    @Test
    public void testIsValidTask_withValidInputs() {
        List<String> testInputs = List.of(ALPHA_NUMERIC,
                ALPHA_WHITESPACE,
                ALPHA_NUMERIC_WHITESPACE,
                ALPHA_NUMERIC_SYMBOL_WHITESPACE);
        for (String input : testInputs) {
            assertTrue(Task.isValidTask(input));
        }
    }

    @Test
    public void testIsValidTask_withInvalidInputs() {
        assertFalse(Task.isValidTask(""));
        assertFalse(Task.isValidTask(" "));
    }

    @Test
    void testEquals_withSameValues() {
        assertEquals(incompleteTask, completeTask);
    }

    @Test
    void testEquals_withSameObject() {
        assertEquals(incompleteTask, incompleteTask);
    }

    @Test
    void testEquals_withNull() {
        assertNotEquals(incompleteTask, null);
    }

    @Test
    void testEquals_withDifferentValues() {
        Task diffTask = new Task("A different Task");
        assertNotEquals(incompleteTask, diffTask);
    }

    @Test
    public void testHashCode() {
        assertEquals(completeTask.hashCode(), incompleteTask.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(String.format("[%s] %s", COMPLETED_STATUS_ICON, TEST_DESCRIPTION), completeTask.toString());
        assertEquals(String.format("[%s] %s", INCOMPLETE_STATUS_ICON, TEST_DESCRIPTION), incompleteTask.toString());
    }
}
