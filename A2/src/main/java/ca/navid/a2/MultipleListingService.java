package ca.navid.a2;

import java.util.UUID;

/**
 * Overly Simplified Multiple Listing Service Record
 * Each MLS record contains a universally unique id and
 * the address and the price of the real estate property.
 * @author Navid Mohaghegh
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html">Java Util UUID</a>
 */
public class MultipleListingService {

    private UUID id;
    private String address;
    private int price;

    /**
     * Get the ID of the property
     * @return the UUID of the property
     */
    public UUID getId() {
        return id;
    }

    /**
     * Set the ID of the property
     * @param id the UUID of the property
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Get the address of the property
     * @return the address of the property
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the property
     * @param address of the property
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the price of the property
     * @return the price of the property
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the price of the property
     * @param price of the property
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Builder design pattern to facilitate construction of an
     * MLS record.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Design_Patterns">About Design Patterns</a>
     * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern">About Builder Pattern</a>
     */
    public static class Builder {

        private UUID id;
        private String address;
        private int price;

        public Builder(UUID id) {
            this.id = id;
        }

        /**
         * Set the address of the property
         * @param address of the property
         * @return the Builder object
         */
        public Builder locatedAt(String address){
            this.address = address;
            return this;
        }

        /**
         * Set the price of the property
         * @param price of the property
         * @return the Builder object
         */
        public Builder pricedAt(int price){
            this.price = price;
            return this;
        }

        /**
         * Finalize the construction of an MLS record using Builder design pattern.
         * @return the MLS record using the previously collected information
         * provided to the Builder object.
         */
        public MultipleListingService build(){
            MultipleListingService mls = new MultipleListingService();
            mls.id = this.id;
            mls.address = this.address;
            mls.price = this.price;
            return mls;
        }
    }
}
