package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Task;
import seedu.address.model.animal.TaskList;


/**
 * The API of the Model component.
 */
public interface AnimalModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Animal> PREDICATE_SHOW_ALL_ANIMALS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(AnimalReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    AnimalReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' catalog file path.
     */
    Path getAnimalCatalogFilePath();

    /**
     * Sets the user prefs' catalog file path.
     */
    void setAnimalCatalogFilePath(Path animalCatalogFilePath);

    /**
     * Replaces catalog data with the data in {@code animalCatalog}.
     */
    void setAnimalCatalog(ReadOnlyAnimalCatalog animalCatalog);

    /** Returns the AnimalCatalog */
    ReadOnlyAnimalCatalog getAnimalCatalog();

    /**
     * Returns true if an animal with the same identity as {@code animal} exists in the catalog.
     */
    boolean hasAnimal(Animal animal);

    /**
     * Deletes the given animal.
     * The animal must exist in the catalog.
     */
    void deleteAnimal(Animal target);

    /**
     * Adds the given animal.
     * {@code animal} must not already exist in the catalog.
     */
    void addAnimal(Animal animal);

    /**
     * Replaces the given animal {@code target} with {@code editedAnimal}.
     * {@code target} must exist in the catalog.
     * The animal identity of {@code editedAnimal} must not be the same as another existing animal in the catalog.
     */
    void setAnimal(Animal target, Animal editedAnimal);

    void addTask(Task newTask, Animal animal);

    /** Returns an unmodifiable view of the filtered animal list */
    ObservableList<Animal> getFilteredAnimalList();

    /**
     * Updates the filter of the filtered animal list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAnimalList(Predicate<Animal> predicate);
}
