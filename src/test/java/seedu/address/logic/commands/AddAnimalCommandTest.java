package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.TOFU;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyAnimalCatalog;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Task;
import seedu.address.testutil.AnimalBuilder;


public class AddAnimalCommandTest {

    @Test
    public void constructor_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAnimalCommand(null));
    }

    @Test
    public void execute_animalAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAnimalAdded modelStub = new ModelStubAcceptingAnimalAdded();
        Animal validAnimal = new AnimalBuilder().build();

        CommandResult commandResult = new AddAnimalCommand(validAnimal).execute(modelStub);

        assertEquals(String.format(AddAnimalCommand.MESSAGE_SUCCESS, AnimalMessages.format(validAnimal)),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validAnimal), modelStub.animalAdded);
    }

    @Test
    public void execute_duplicateAnimal_throwsCommandException() {
        Animal validAnimal = new AnimalBuilder().build();
        AddAnimalCommand addAnimalCommand = new AddAnimalCommand(validAnimal);
        ModelStub modelStub = new ModelStubWithAnimal(validAnimal);

        assertThrows(CommandException.class, AddAnimalCommand.MESSAGE_DUPLICATE_ANIMAL, ()
                -> addAnimalCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Animal tofu = new AnimalBuilder().withName("Tofu").build();
        Animal muffin = new AnimalBuilder().withName("Muffin").build();
        AddAnimalCommand addTofuCommand = new AddAnimalCommand(tofu);
        AddAnimalCommand addMuffinCommand = new AddAnimalCommand(muffin);

        // same object -> returns true
        assertEquals(addTofuCommand, addTofuCommand);

        // same values -> returns true
        AddAnimalCommand addTofuCommandCopy = new AddAnimalCommand(tofu);
        assertEquals(addTofuCommand, addTofuCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addTofuCommand);

        // null -> returns false
        assertNotEquals(null, addTofuCommand);

        // different person -> returns false
        assertNotEquals(addTofuCommand, addMuffinCommand);
    }

    @Test
    public void toStringMethod() {
        AddAnimalCommand addAnimalCommand = new AddAnimalCommand(TOFU);
        String expected = AddAnimalCommand.class.getCanonicalName() + "{toAdd=" + TOFU + "}";
        assertEquals(expected, addAnimalCommand.toString());
    }

    /**
     * A default model stub that have all its methods failing.
     */
    private static class ModelStub implements AnimalModel {
        private static final String INVALID_METHOD_CALL = "This method should not be called.";
        @Override
        public void setUserPrefs(AnimalReadOnlyUserPrefs userPrefs) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public AnimalReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public Path getAnimalCatalogFilePath() {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void setAnimalCatalogFilePath(Path addressBookFilePath) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void addAnimal(Animal animal) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void setAnimalCatalog(ReadOnlyAnimalCatalog newData) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public ReadOnlyAnimalCatalog getAnimalCatalog() {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public boolean hasAnimal(Animal animal) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void deleteAnimal(Animal target) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void setAnimal(Animal target, Animal editedAnimal) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void addTask(Task newTask, Animal animal) { throw new AssertionError(INVALID_METHOD_CALL); }

        @Override
        public ObservableList<Animal> getFilteredAnimalList() {
            throw new AssertionError(INVALID_METHOD_CALL);
        }

        @Override
        public void updateFilteredAnimalList(Predicate<Animal> predicate) {
            throw new AssertionError(INVALID_METHOD_CALL);
        }
    }

    /**
     * A Model stub that contains a single animal.
     */
    private static class ModelStubWithAnimal extends ModelStub {
        private final Animal animal;

        ModelStubWithAnimal(Animal animal) {
            requireNonNull(animal);
            this.animal = animal;
        }

        @Override
        public boolean hasAnimal(Animal animal) {
            requireNonNull(animal);
            return this.animal.isSameAnimal(animal);
        }
    }

    /**
     * A Model stub that always accept the animal being added.
     */
    private static class ModelStubAcceptingAnimalAdded extends ModelStub {
        final ArrayList<Animal> animalAdded = new ArrayList<>();

        @Override
        public boolean hasAnimal(Animal animal) {
            requireNonNull(animal);
            return animalAdded.stream().anyMatch(animal::isSameAnimal);
        }

        @Override
        public void addAnimal(Animal animal) {
            requireNonNull(animal);
            animalAdded.add(animal);
        }

        @Override
        public ReadOnlyAnimalCatalog getAnimalCatalog() {
            return new AnimalCatalog();
        }
    }

}
