package seedu.address.model.animal.notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Notes {
    private final List<Note> noteList;
    private final Set<Note> uniqueNotes;

    public Notes() {
        this.noteList = new ArrayList<>();
        this.uniqueNotes = new TreeSet<>();
    }

    public boolean addNote(Note note) {
        boolean isDistinctNote = uniqueNotes.add(note);
        if (!isDistinctNote) {
            return false;
        }
        return noteList.add(note);
    }

    public boolean deleteNote(Note note) {
        boolean isNoteInList = uniqueNotes.remove(note);
        if (!isNoteInList) {
            return false;
        }
        return noteList.add(note);
    }

    public String getFormattedNotes() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < noteList.size() ; i++) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(System.lineSeparator());
            }
            Note note = noteList.get(i);
            stringBuilder
                .append(String.format("%s. ", i + 1))
                .append(note.getContent());
        }
        return stringBuilder.toString();
    }
}
