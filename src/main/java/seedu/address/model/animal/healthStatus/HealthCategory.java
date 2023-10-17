package seedu.address.model.animal.healthStatus;

public enum HealthCategory {
    ILLNESS("Illness(es)"),
    DIETARY_RESTRICTION("Dietary Restriction(s)"),
    ALLERGY_INFO("Allergy Information"),
    VACCINATION_STATUS("Vaccination Status"),
    STERILIZATION_STATUS("Sterilization Status");

    private final String displayName;

    HealthCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
