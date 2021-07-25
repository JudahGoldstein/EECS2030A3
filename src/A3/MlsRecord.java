package A3;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * The class to encapsulate the listing's UUID, the listing's sellers, and certain high level attributes in a CharacteristicsList
 *
 */

public class MlsRecord implements HighValuable {
    private UUID uuid;
    private CharacteristicsList recordItems;
    private Set<SimpleParticipantType> participants;

    /**
     * Encapsulate a listing's high level attributes
     *
     * @param listingObject the Property to be stored
     * @param broker        the Brokerage presenting the listing
     * @param participants  the sellers of the property
     * @param modified      the date and time that the entry was last modified
     * @param disclaimer    a disclaimer
     */
    public MlsRecord(Property listingObject, BusinessType broker, Set<SimpleParticipantType> participants, Date modified, String disclaimer) {
        this.uuid = UUID.randomUUID();
        this.participants = participants;
        recordItems = new CharacteristicsList(uuid.toString());
        recordItems.add(new Characteristic<>(uuid.toString() + "-Property", listingObject));
        recordItems.add(new Characteristic<>(uuid.toString() + "-Brokerage", broker));
        recordItems.add(new Characteristic<>(uuid.toString() + "-LastModified", modified));
        recordItems.add(new Characteristic<>(uuid.toString() + "-Disclaimer", disclaimer));
    }

    /**
     * Get the stored Property
     * @return the stored Property
     */
    public Property getListingObject() {
        return (Property) recordItems.getByName(uuid.toString() + "-Property").getValue();
    }

    /**
     * Set the stored Property
     * @param listingObject the Property to be stored
     */
    public void setListingObject(Property listingObject) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-Property", listingObject));
        this.setLastModified(new Date());
    }

    /**
     * Get the Brokerage that is presenting the listing
     * @return the stored Brokerage
     */
    public BusinessType getBroker() {
        return (BusinessType) recordItems.getByName(uuid.toString() + "-Broker").getValue();
    }

    /**
     * Set the Brokerage that is presenting the listing
     * @param broker the Brokerage to be stored
     */
    public void setBroker(BusinessType broker) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-Broker", broker));
        this.setLastModified(new Date());
    }

    /**
     * Get the list of participants
     * @return the list of sellers
     */
    public Set<SimpleParticipantType> getParticipants() {
        return this.participants;
    }

    /**
     * Set the list of participants
     * @param participants a list of participants to be stored
     */
    public void setParticipants(Set<SimpleParticipantType> participants) {
        this.participants = participants;
        this.setLastModified(new Date());
    }

    /**
     * Add a participant to the list of participants
     * @param participant the participant to add
     */
    public void addParticipant(SimpleParticipantType participant) {
        this.participants.add(participant);
        this.setLastModified(new Date());
    }

    /**
     * Remove a participant from the list of participants
     * @param participant the participant to remove
     */
    public void removeParticipant(SimpleParticipantType participant) {
        this.participants.remove(participant);
    }

    /**
     * Get the date and time that the MlsRecord was last modified
     * @return the date and time that the record was last modified
     */
    public Date getLastModified() {
        return (Date) recordItems.getByName(uuid.toString() + "-LastModified").getValue();
    }

    /**
     * Set the date and time that the MlsRecord was last modified
     * @param modified the date and time at which the record was last modified
     */
    public void setLastModified(Date modified) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-LastModified", modified));
    }

    /**
     * Get the MLS Database disclaimer
     *
     * @return Disclaimer
     */
    public String getDisclaimer() {
        return (String) recordItems.getByName(uuid.toString() + "-Disclaimer").getValue();
    }

    /**
     * Set the MLS Database disclaimer
     *
     * @param disclaimer to be stored
     */
    public void setDisclaimer(String disclaimer) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-Disclaimer", disclaimer));
        this.setLastModified(new Date());
    }

    /**
     * Gets a copy of the UUID of the listing
     * Uses composition in order to protect its copy of its assigned UUID
     *
     * @return a deep copy of the listing UUID
     */
    public UUID getUUID() {
        return UUID.fromString(uuid.toString()); // creates a deep copy of the UUID which is returned
    }

    /**
     * Indicates whether or not a property is high value (worth $4 million or more)
     *
     * @return a boolean to indicate if the property is high value or not
     */
    public boolean isHighValue() {
        Property listing = this.getListingObject();
        return (double) listing.getCharacteristics().getByName(listing.getName() + "-Price").getValue() >= HIGH_VALUE_LOWEST;
    }
}
