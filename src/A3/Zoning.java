package A3;

public enum Zoning {
    // residential, agricultural, recreational, commercial, industrial, etc.....
    RESIDENTIAL("Residential"),
    AGRICULTURAL("Agricultural"),
    RECREATIONAL("Recreational"),
    COMMERCIAL("Commercial"),
    INDUSTRIAL("Industrial"),
    DEFAULT(RESIDENTIAL);

    private String description;

    Zoning(String description) { this.description = description; }
    Zoning(Zoning zone) { this.description = zone.description; }

    public String getDescription() { return description; }
}
