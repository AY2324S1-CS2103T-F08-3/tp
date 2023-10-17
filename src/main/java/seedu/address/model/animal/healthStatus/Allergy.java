package seedu.address.model.animal.healthStatus;

import seedu.address.model.animal.notes.Note;
import seedu.address.model.animal.notes.PriorityEnum;

public class Allergy extends Note {
    private final String allergy;

    public Allergy(String allergy) {
        super(PriorityEnum.CRITICAL);
        this.allergy = allergy;
    }

    @Override
    public String getContent() {
        return allergy;
    }
}
