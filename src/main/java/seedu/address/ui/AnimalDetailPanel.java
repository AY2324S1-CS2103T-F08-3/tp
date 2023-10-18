package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.animal.Animal;

public class AnimalDetailPanel extends UiPart<Region> {
    private static final String FXML = "AnimalDetailPanel.fxml";

    @FXML
    private Label name;
    @FXML
    private Label petId;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label sex;
    @FXML
    private Label breed;
    @FXML
    private Label admissionDate;

    /**
     * Creates an empty detailed view panel upon initialization.
     */
    public AnimalDetailPanel() {
        super(FXML);
        clearDetails();
    }

    /**
     * Update the UI with the details of the provided person.
     */
    public void updateDetails(Animal animal) {
        name.setText(animal.getName().fullName);
        petId.setText(animal.getPetId().toString());
        dateOfBirth.setText(animal.getDateOfBirth().toString());
        sex.setText(animal.getSex().toString());
        breed.setText(animal.getBreed().toString());
        admissionDate.setText(animal.getAdmissionDate().toString());
    }

    /**
     * Clear the details (For displaying a blank panel)
     */
    public void clearDetails() {
        name.setText("");
        petId.setText("");
        dateOfBirth.setText("");
        sex.setText("");
        breed.setText("");
        admissionDate.setText("");
    }
}