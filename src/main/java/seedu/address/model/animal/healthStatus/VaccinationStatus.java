package seedu.address.model.animal.healthStatus;

import seedu.address.model.animal.notes.Note;
import seedu.address.model.animal.notes.PriorityEnum;

public class VaccinationStatus extends Note {
    private String vaccinationStatus;

    public VaccinationStatus(String vaccinationStatus) {
        super(PriorityEnum.NORMAL);
        this.vaccinationStatus = vaccinationStatus;
    }

    @Override
    public String getContent() {
        return vaccinationStatus;
    }
}
