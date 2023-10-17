package seedu.address.model.animal.notes;

import java.util.Objects;

public abstract class Note implements Comparable<Note> {
    private final PriorityEnum priority;

    protected Note(PriorityEnum priority) {
        this.priority = priority;
    }

    public abstract String getContent();

    public PriorityEnum getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        // Self check
        if (this == obj) {
            return true;
        }

        // Null check and type check
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast and field comparison
        Note note = (Note) obj;
        return Objects.equals(getContent(), note.getContent()) &&
            Objects.equals(getPriority(), note.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), getPriority());
    }

    @Override
    public int compareTo(Note other) {
        return this.getPriority().compareTo(other.getPriority());
    }
}
