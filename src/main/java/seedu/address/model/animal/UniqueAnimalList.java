package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.animal.exceptions.AnimalNotFoundException;
import seedu.address.model.animal.exceptions.DuplicateAnimalException;

/**
 * A list of animals that enforces uniqueness between its elements and does not allow nulls.
 * An animal is considered unique by comparing using {@code Animal#isSameAnimal(Animal)}. As such, adding and updating of
 * animals uses Animal#isSameAnimal(Animal) for equality so as to ensure that the animal being added or updated is
 * unique in terms of identity in the UniqueAnimalList. However, the removal of an animal uses Animal#equals(Object) so
 * as to ensure that the animal with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Animal#isSameAnimal(Animal)
 */
public class UniqueAnimalList implements Iterable<Animal> {

    private final ObservableList<Animal> internalList = FXCollections.observableArrayList();
    private final ObservableList<Animal> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent animal as the given argument.
     */
    public boolean contains(Animal toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAnimal);
    }

    /**
     * Adds an animal to the list.
     * The animal must not already exist in the list.
     */
    public void add(Animal toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAnimalException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the animal {@code target} in the list with {@code editedAnimal}.
     * {@code target} must exist in the list.
     * The animal identity of {@code editedAnimal} must not be the same as another existing animal in the list.
     */
    public void setAnimal(Animal target, Animal editedAnimal) {
        requireAllNonNull(target, editedAnimal);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AnimalNotFoundException();
        }

        if (!target.isSameAnimal(editedAnimal) && contains(editedAnimal)) {
            throw new DuplicateAnimalException();
        }

        internalList.set(index, editedAnimal);
    }

    /**
     * Removes the equivalent animal from the list.
     * The animal must exist in the list.
     */
    public void remove(Animal toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AnimalNotFoundException();
        }
    }

    public void setAnimals(UniqueAnimalList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code animals}.
     * {@code animals} must not contain duplicate animals.
     */
    public void setAnimals(List<Animal> animals) {
        requireAllNonNull(animals);
        if (!animalsAreUnique(animals)) {
            throw new DuplicateAnimalException();
        }

        internalList.setAll(animals);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Animal> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Animal> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueAnimalList)) {
            return false;
        }

        UniqueAnimalList otherUniquePersonList = (UniqueAnimalList) other;
        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code animals} contains only unique animals.
     */
    private boolean animalsAreUnique(List<Animal> animals) {
        for (int i = 0; i < animals.size() - 1; i++) {
            for (int j = i + 1; j < animals.size(); j++) {
                if (animals.get(i).isSameAnimal(animals.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
