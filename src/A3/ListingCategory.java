package A3;

public enum ListingCategory {
    // purchase, lease, and rent
    PURCHASE("Purchase"),
    LEASE("Lease"),
    RENT("Rent"),
    DEFAULT(PURCHASE);

    private String description;

    ListingCategory(String description) { this.description = description; }
    ListingCategory(ListingCategory listingType) { this.description = listingType.description; }

    public String getDescription() { return description; }
}
