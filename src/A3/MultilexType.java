package A3;

public enum MultilexType {
    // duplex, triplex, quadplex
    NONE("None"),
    DUPLEX("Duplex"),
    TRIPLEX("Triplex"),
    QUADRUPLEX("Quadruplex"),
    DEFAULT(NONE);

    private String description;

    MultilexType(String description) { this.description = description; }
    MultilexType(MultilexType multilex) { this.description = multilex.description; }

    public String getDescription() { return description; }
}
