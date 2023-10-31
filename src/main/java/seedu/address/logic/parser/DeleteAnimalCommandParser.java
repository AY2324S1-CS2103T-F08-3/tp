package seedu.address.logic.parser;

import static seedu.address.logic.AnimalMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
// To implement Parser<DeleteAnimalCommand>
public class DeleteAnimalCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAnimalCommand parse(String args) throws ParseException {
        try {
            Index index = AnimalParserUtil.parseIndex(args);
            return new DeleteAnimalCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAnimalCommand.MESSAGE_USAGE), pe);
        }
    }

}
