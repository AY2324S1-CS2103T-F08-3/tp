package seedu.address.model.animal.healthStatus;

import seedu.address.model.animal.notes.Note;
import seedu.address.model.animal.notes.PriorityEnum;

public class DietaryRestriction extends Note {
    private final String dietaryRestriction;

    public DietaryRestriction(String dietaryRestriction) {
        super(PriorityEnum.CRITICAL);
        this.dietaryRestriction = dietaryRestriction;
    }

    @Override
    public String getContent() {
        return dietaryRestriction;
    }
}
