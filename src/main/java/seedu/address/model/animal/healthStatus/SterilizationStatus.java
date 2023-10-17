package seedu.address.model.animal.healthStatus;

import seedu.address.model.animal.notes.Note;
import seedu.address.model.animal.notes.PriorityEnum;

public class SterilizationStatus extends Note {
    private String sterilizationStatus;

    public SterilizationStatus(String sterilizationStatus) {
        super(PriorityEnum.NORMAL);
        this.sterilizationStatus = sterilizationStatus;
    }

    @Override
    public String getContent() {
        return sterilizationStatus;
    }
}
