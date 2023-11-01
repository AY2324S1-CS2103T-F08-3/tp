package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PET_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;

/**
 * Adds a animal to the catalog.
 */

public class AddAnimalCommand extends AnimalCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an animal to the Catalog. "
            + "Parameters: "
            + NAME.getUsage() + " "
            + PET_ID.getUsage() + " "
            + DATE_OF_BIRTH.getUsage() + " "
            + DATE_OF_ADMISSION.getUsage() + " "
            + SPECIES.getUsage() + " "
            + SEX.getUsage() + " "
            + BREED.getUsage();

    public static final String EXAMPLE_USAGE = "Example: " + COMMAND_WORD + " "
        + NAME.getPrefix() + "Pookie "
        + PET_ID.getPrefix() + "1234 "
        + SEX.getPrefix() + "MALE "
        + DATE_OF_BIRTH.getPrefix() + "2019-01-01 "
        + DATE_OF_ADMISSION.getPrefix() + "2019-02-02 "
        + SPECIES.getPrefix() + "Dog "
        + BREED.getPrefix() + "Poodle";

    public static final String MESSAGE_SUCCESS = "New animal added: %s";

    public static final String MESSAGE_DUPLICATE_ANIMAL = "This animal already exists in the Catalog";

    private final Animal toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Animal}
     */
    public AddAnimalCommand(Animal animal) {
        requireNonNull(animal);
        toAdd = animal;
    }

    @Override
    public CommandResult execute(AnimalModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasAnimal(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIMAL);
        }

        model.addAnimal(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, AnimalMessages.format(toAdd)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAnimalCommand // instanceof handles nulls
                && toAdd.equals(((AddAnimalCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
