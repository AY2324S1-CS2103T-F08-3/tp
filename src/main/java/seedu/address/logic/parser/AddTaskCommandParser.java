package seedu.address.logic.parser;

import static seedu.address.logic.AnimalMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.AnimalParserUtil.ParsedTaskInput;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.Task;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements AnimalParser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand.
     * and returns an AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddTaskCommand parse(String args) throws ParseException {
        try {
            ParsedTaskInput parsedTaskInput = AnimalParserUtil.parseTaskInput(args);
            Index targetIndex = parsedTaskInput.getTargetIndex();
            Task taskDescription = parsedTaskInput.getTaskDescription();

            return new AddTaskCommand(targetIndex, taskDescription);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
