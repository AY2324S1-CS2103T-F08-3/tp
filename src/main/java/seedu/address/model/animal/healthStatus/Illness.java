package seedu.address.model.animal.healthStatus;

import seedu.address.model.animal.notes.Note;
import seedu.address.model.animal.notes.PriorityEnum;

public class Illness extends Note {
    private final String illness;

    public Illness(String illness) {
        super(PriorityEnum.CRITICAL);
        this.illness = illness;
    }

    @Override
    public String getContent() {
        return illness;
    }
}
