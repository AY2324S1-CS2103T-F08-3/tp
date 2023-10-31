package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliAnimalSyntax.BREED;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_ADMISSION;
import static seedu.address.logic.parser.CliAnimalSyntax.DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliAnimalSyntax.NAME;
import static seedu.address.logic.parser.CliAnimalSyntax.PET_ID;
import static seedu.address.logic.parser.CliAnimalSyntax.SEX;
import static seedu.address.logic.parser.CliAnimalSyntax.SPECIES;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.AddAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTaskCommandParser implements AnimalParser<AddTaskCommand> {
    public static final Prefix[] MANDATORY_PREFIXES = CliAnimalSyntax.getMandatoryPrefixes().toArray(Prefix[]::new);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand.
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddAnimalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, MANDATORY_PREFIXES);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(AnimalMessages.MESSAGE_INVALID_PREAMBLE);
        }

        if (!ArgumentMultimap.arePrefixesPresent(argMultimap, MANDATORY_PREFIXES)) {
            // Gets the prefix usage string.
            List<String> missingPrefixesUsage = ArgumentMultimap.getMissingPrefixes(argMultimap, MANDATORY_PREFIXES)
                    .stream()
                    .map(CliAnimalSyntax::getCliSyntaxFromPrefix)
                    .map(CliAnimalSyntax::getUsage)
                    .collect(Collectors.toList());

            throw new ParseException(getHelpMessage(missingPrefixesUsage));
        }

        // ParseException containing the duplicated prefixes separated by whitespace is thrown.
        argMultimap.verifyNoDuplicatePrefixesFor(MANDATORY_PREFIXES);

        // Optional::orElseThrow should never throw a NoSuchElementException given the checks done above.
        Name name = AnimalParserUtil.parseName(argMultimap.getValue(NAME).orElseThrow());
        PetId id = AnimalParserUtil.parsePetId(argMultimap.getValue(PET_ID).orElseThrow());
        DateOfBirth dob = AnimalParserUtil.parseDateOfBirth(argMultimap.getValue(DATE_OF_BIRTH).orElseThrow());
        AdmissionDate doa = AnimalParserUtil.parseAdmissionDate(argMultimap.getValue(DATE_OF_ADMISSION).orElseThrow());
        Species species = AnimalParserUtil.parseSpecies(argMultimap.getValue(SPECIES).orElseThrow());
        Sex sex = AnimalParserUtil.parseSex(argMultimap.getValue(SEX).orElseThrow());
        Breed breed = AnimalParserUtil.parseBreed(argMultimap.getValue(BREED).orElseThrow());

        Animal animal = new Animal(name, id, species, breed, sex, doa, dob);

        return new AddAnimalCommand(animal);
    }

    /**
     * Returns the formatted help message.
     * Displays the following Help string:
     *         Invalid Command:
     *         Missing prefixes: n/ [NAME], i/ [PET_ID]
     *         Example: add n/ Pookie ...
     *
     * @param missingPrefixesUsage the missing prefixes' usage string.
     * @return a formatted help message consisting of the content specified above.
     */
    public static String getHelpMessage(List<String> missingPrefixesUsage) {
        return AnimalMessages.getFormattedHelpMessage(
                AnimalMessages.MESSAGE_INVALID_COMMAND,
                String.format(AnimalMessages.MESSAGE_MISSING_PREFIXES_FORMAT, String.join(", ", missingPrefixesUsage)),
                AddAnimalCommand.EXAMPLE_USAGE
        );
    }
}
