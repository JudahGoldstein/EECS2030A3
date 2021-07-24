package A3;

public enum DetachedType {
    DETACHED("Detached"),
    SEMI_DETACHED("Semi-Detached"),
    NOT_DETACHED("Not Detatched"),
    DEFAULT(DETACHED); // assume detached by default

    private String description;

    DetachedType(String description) { this.description = description; }
    DetachedType(DetachedType isDetached) { this.description = isDetached.description; }

    public String getDescription() { return description; }
}
