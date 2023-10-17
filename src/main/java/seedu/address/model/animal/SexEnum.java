package seedu.address.model.animal;

public enum SexEnum {
    MALE("M"),
    FEMALE("F");

    private final String shortForm;

    SexEnum(String shortForm) {
        this.shortForm = shortForm;
    }

    public String getShortForm() {
        return shortForm;
    }

    public static boolean isValid(String test) {
        for (SexEnum value : SexEnum.values()) {
            if (value.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }
}
