package seedu.address.model.animal.healthStatus;

import java.util.EnumMap;

import seedu.address.model.animal.notes.Note;
import seedu.address.model.animal.notes.Notes;

public class HealthStatus {
    private final EnumMap<HealthCategory, Notes> healthNotes;

    public HealthStatus() {
        this.healthNotes = new EnumMap<>(HealthCategory.class);
        // Initialize with empty Notes for each category
        for (HealthCategory category : HealthCategory.values()) {
            this.healthNotes.put(category, new Notes());
        }
    }

    public String getFormattedHealthNotes(HealthCategory healthCategory) {
        Notes notes = healthNotes.get(healthCategory);
        return notes.getFormattedNotes();
    }

    public boolean addHealthItem(HealthCategory healthCategory, Note toAdd) {
        Notes notes = healthNotes.get(healthCategory);
        return notes.addNote(toAdd);
    }

    public boolean deleteHealthItem(HealthCategory healthCategory, Note item) {
        Notes notes = healthNotes.get(healthCategory);
        return notes.deleteNote(item);
    }
}

