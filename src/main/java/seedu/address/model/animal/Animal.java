package seedu.address.model.animal;

import seedu.address.model.animal.healthStatus.HealthStatus;
import seedu.address.model.animal.notes.Notes;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Animal {
    private final Name name;
    private final PetId petId;
    private final DateOfBirth dateOfBirth;
    private final Sex sex;
    private final Species species;
    private final Breed breed;
    private final AdmissionDate admissionDate;
    private final HealthStatus healthStatus;
    private final Notes otherNotes;

    public Animal(Name name, PetId petId, Species species, Breed breed, Sex sex,
                  AdmissionDate admissionDate, DateOfBirth dateOfBirth) {
        this.name = name;
        this.petId = petId;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.admissionDate = admissionDate;
        this.dateOfBirth = dateOfBirth;
        this.healthStatus = new HealthStatus();
        this.otherNotes = new Notes();
    }

    public Name getName() {
        return name;
    }

    public PetId getPetId() {
        return petId;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public AdmissionDate getAdmissionDate() {
        return admissionDate;
    }

    public Sex getSex() {
        return sex;
    }

    public Breed getBreed() {
        return breed;
    }


    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public Notes getNotes() {
        return otherNotes;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return 1;
    }
}