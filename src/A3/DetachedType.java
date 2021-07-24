package A3;

public enum DetachedType {
    // duplex, triplex, quadplex
    DETACHED("Detached"),
    SEMI_DETACHED("Semi-Detached"),
    NOT_DETACHED("Not Detatched"),
    DEFAULT(DETACHED);

    private String description;

    DetachedType(String description) { this.description = description; }
    DetachedType(DetachedType isDetached) { this.description = isDetached.description; }

    public String getDescription() { return description; }
}
