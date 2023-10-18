package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.animal.Animal;


/**
 * The API of the Model component.
 */
public interface AnimalModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Animal> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAnimalCatalogFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAnimalCatalogFilePath(Path animalCatalogFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAnimalCatalog getAnimalCatalog();

    /**
     * Returns true if an animal with the same identity as {@code animal} exists in the address book.
     */
    boolean hasAnimal(Animal animal);

    /**
     * Deletes the given animal.
     * The animal must exist in the address book.
     */
    void deleteAnimal(Animal target);

    /**
     * Adds the given animal.
     * {@code animal} must not already exist in the address book.
     */
    void addAnimal(Animal animal);

    /**
     * Replaces the given animal {@code target} with {@code editedAnimal}.
     * {@code target} must exist in the address book.
     * The animal identity of {@code editedAnimal} must not be the same as another existing animal in the address book.
     */
    void setAnimal(Animal target, Animal editedAnimal);

    /** Returns an unmodifiable view of the filtered animal list */
    ObservableList<Animal> getFilteredAnimalList();

    /**
     * Updates the filter of the filtered animal list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAnimalList(Predicate<Animal> predicate);
}
