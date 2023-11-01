package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;
import seedu.address.model.animal.Task;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class AnimalParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String id} into a {@code id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static PetId parsePetId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!PetId.isValidPetId(trimmedId)) {
            throw new ParseException(PetId.MESSAGE_CONSTRAINTS);
        }
        return new PetId(id);
    }
    /**
     * Parses a {@code String species} into a {@code species}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code species} is invalid.
     */
    public static Species parseSpecies(String species) throws ParseException {
        requireNonNull(species);
        String trimmedSpecies = species.trim();
        if (!Species.isValidSpecies(trimmedSpecies)) {
            throw new ParseException(Species.MESSAGE_CONSTRAINTS);
        }
        return new Species(species);
    }

    /**
     * Parses a {@code String breed} into a {@code breed}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code breed} is invalid.
     */
    public static Breed parseBreed(String breed) throws ParseException {
        requireNonNull(breed);
        String trimmedBreed = breed.trim();
        if (!Breed.isValidBreed(trimmedBreed)) {
            throw new ParseException(Breed.MESSAGE_CONSTRAINTS);
        }
        return new Breed(breed);
    }

    /**
     * Parses a {@code String sex} into a {@code sex}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sex} is invalid.
     */
    public static Sex parseSex(String sex) throws ParseException {
        requireNonNull(sex);
        String trimmedSex = sex.trim();
        if (!Sex.isValidSex(trimmedSex)) {
            throw new ParseException(Sex.MESSAGE_CONSTRAINTS);
        }
        return new Sex(sex);
    }

    /**
     * Parses a {@code String dob} into a {@code dob}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dob} is invalid.
     */
    public static DateOfBirth parseDateOfBirth(String dob) throws ParseException {
        requireNonNull(dob);
        String trimmedDob = dob.trim();
        if (!DateOfBirth.isValidDate(trimmedDob)) {
            throw new ParseException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        return new DateOfBirth(dob);
    }

    /**
     * Parses a {@code String doa} into a {@code doa}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code doa} is invalid.
     */
    public static AdmissionDate parseAdmissionDate(String doa) throws ParseException {
        requireNonNull(doa);
        String trimmedDoa = doa.trim();
        if (!AdmissionDate.isValidDate(trimmedDoa)) {
            throw new ParseException(AdmissionDate.MESSAGE_CONSTRAINTS);
        }
        return new AdmissionDate(doa);
    }

    /**
     * Parses the given {@code input} into a {@code ParsedTaskInput} object.
     * <p>
     * The input is expected to be in the format: index taskDescription,
     * where index represents the target animal's position in the list (as a positive integer),
     * and task description is a description of the task to be added.
     * </p>
     * <p>
     * The method uses regular expressions to extract the index and task description parts
     * and then constructs a {@code ParsedTaskInput} object.
     * </p>
     *
     * @param input The string input to be parsed.
     * @return A {@code ParsedTaskInput} object containing the parsed target index and task description.
     * @throws ParseException if the given {@code input} is invalid or does not conform to the expected format.
     */
    public static ParsedTaskInput parseTaskInput(String input) throws ParseException {
        String trimmedInput = input.trim();
        Pattern pattern = Pattern.compile("^(\\d+)\\s+(.+)$");
        Matcher matcher = pattern.matcher(trimmedInput);

        if (!matcher.matches()) {
            throw new ParseException("Invalid input format. Expected: <index> <task description>");
        }

        String indexStr = matcher.group(1);
        String taskDescription = matcher.group(2);

        if (!StringUtil.isNonZeroUnsignedInteger(indexStr)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        Index index = Index.fromOneBased(Integer.parseInt(indexStr));
        return new ParsedTaskInput(index, taskDescription);
    }

    /**
     * Represents the result of parsing the user input for adding a task.
     */
    public static class ParsedTaskInput {
        private final Index targetIndex;
        private final String taskDescription;

        /**
         * Constructs a {@code ParsedTaskInput} object with the given target index and task description.
         *
         * @param targetIndex    The target index of the animal to which the task is to be added.
         * @param taskDescription The description of the task to be added.
         */
        public ParsedTaskInput(Index targetIndex, String taskDescription) {
            this.targetIndex = targetIndex;
            this.taskDescription = taskDescription;
        }

        public Index getTargetIndex() {
            return targetIndex;
        }

        public Task getTaskDescription() {
            return new Task(taskDescription);
        }

        @Override
        public int hashCode() {
            return Objects.hash(targetIndex, taskDescription);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof ParsedTaskInput // instanceof handles nulls
                    && targetIndex.equals(((ParsedTaskInput) other).targetIndex)
                    && taskDescription.equals(((ParsedTaskInput) other).taskDescription));
        }
    }


}

