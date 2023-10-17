package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * UI Component that displays detailed information of a Person when selected.
 */
public class PersonDetailPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailPanel.fxml";

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;

    /**
     * Creates an empty detailed view panel upon initialization.
     */
    public PersonDetailPanel() {
        super(FXML);
        clearDetails();
    }

    /**
     * Update the UI with the details of the provided person.
     */
    public void updateDetails(Person person) {
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
    }

    /**
     * Clear the details (For displaying a blank panel)
     */
    public void clearDetails() {
        name.setText("");
        phone.setText("");
        address.setText("");
        email.setText("");
    }
}
