package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AnimalModel.PREDICATE_SHOW_ALL_ANIMALS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Task;

import java.util.List;

/**
 * Adds a animal to the catalog.
 */

public class AddTaskCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "addtask";

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " 1 Feed";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a task to the animal identified by the index number used in the displayed animal list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + EXAMPLE_USAGE;

    public static final String MESSAGE_SUCCESS = "New task added: %s";

    private final Index targetIndex;

    private final Task task;

    /**
     * @param targetIndex of the person in the filtered person list to edit
     * @param task to be added to the animal task list
     */
    public AddTaskCommand(Index targetIndex, Task task) {
        requireNonNull(targetIndex);
        requireNonNull(task);

        this.targetIndex = targetIndex;
        this.task = task;
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);
        List<Animal> lastShownList = model.getFilteredAnimalList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX);
        }

        Animal animalToEdit = lastShownList.get(targetIndex.getZeroBased());
        Animal editedAnimal = createUpdatedTaskAnimal(animalToEdit, task);

        model.setAnimal(animalToEdit, editedAnimal);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, AnimalMessages.format(task)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Animal createUpdatedTaskAnimal(Animal animalToEdit, Task task) {
        assert animalToEdit != null;

        Animal edittedAnimal = new Animal(animalToEdit.getName(), animalToEdit.getPetId(), animalToEdit.getSpecies(),
                animalToEdit.getBreed(), animalToEdit.getSex(), animalToEdit.getAdmissionDate(),
                animalToEdit.getDateOfBirth());

        edittedAnimal.addTask(task);

        return edittedAnimal;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && targetIndex.equals(((AddTaskCommand) other).targetIndex)
                && task.equals(((AddTaskCommand) other).task));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("task", task)
                .toString();
    }
}

