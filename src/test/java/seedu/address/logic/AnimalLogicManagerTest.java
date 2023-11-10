package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX;
import static seedu.address.logic.AnimalMessages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ADMISSION_DATE_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_BREED_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_DATE_OF_BIRTH_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_ID_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_NAME_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SEX_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.PREFIX_SPECIES_TOFU;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddAnimalCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListAnimalCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.ReadOnlyAnimalCatalog;
import seedu.address.storage.AnimalStorageManager;
import seedu.address.storage.JsonAnimalCatalogStorage;
import seedu.address.storage.JsonAnimalUserPrefsStorage;

public class AnimalLogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private AnimalModel model = new AnimalModelManager();
    private AnimalLogic logic;

    @BeforeEach
    public void setUp() {
        JsonAnimalCatalogStorage animalCatalogStorage =
                new JsonAnimalCatalogStorage(temporaryFolder.resolve("animalCatalog.json"));
        JsonAnimalUserPrefsStorage userPrefsStorage =
                new JsonAnimalUserPrefsStorage(temporaryFolder.resolve("animalUserPrefs.json"));
        AnimalStorageManager storage = new AnimalStorageManager(animalCatalogStorage, userPrefsStorage);
        logic = new AnimalLogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListAnimalCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListAnimalCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                AnimalLogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                AnimalLogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredAnimalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredAnimalList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedAnimalModel} <br>
     * @see #assertCommandFailure(String, Class, String, AnimalModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      AnimalModel expectedAnimalModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedAnimalModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, AnimalModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, AnimalModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, AnimalModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        AnimalModel expectedAnimalModel = new AnimalModelManager(model.getAnimalCatalog(), new AnimalUserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedAnimalModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedAnimalModel} <br>
     * @see #assertCommandSuccess(String, String, AnimalModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, AnimalModel expectedAnimalModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedAnimalModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject AnimalLogicManager with an AnimalCatalogStorage that throws the IOException e when saving
        JsonAnimalCatalogStorage animalCatalogStorage = new JsonAnimalCatalogStorage(prefPath) {
            @Override
            public void saveAnimalCatalog(ReadOnlyAnimalCatalog animalCatalog, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonAnimalUserPrefsStorage userPrefsStorage =
                new JsonAnimalUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        AnimalStorageManager storage = new AnimalStorageManager(animalCatalogStorage, userPrefsStorage);

        logic = new AnimalLogicManager(model, storage);

        // Triggers the saveAnimalCatalog method by executing an add command
        String addAnimalCommand = AddAnimalCommand.COMMAND_WORD + PREFIX_NAME_TOFU + PREFIX_SEX_TOFU
                + PREFIX_SPECIES_TOFU + PREFIX_BREED_TOFU + PREFIX_DATE_OF_BIRTH_TOFU
                + PREFIX_ADMISSION_DATE_TOFU + PREFIX_ID_TOFU;
        assertCommandFailure(addAnimalCommand, CommandException.class, expectedMessage, model);
    }
}
