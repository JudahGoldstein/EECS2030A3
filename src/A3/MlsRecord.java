package A3;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class MlsRecord implements HighValuable {
    private UUID uuid;
    private CharacteristicsList recordItems;
    private Set<SimpleParticipantType> participants;

    public MlsRecord(Property listingObject, BusinessType broker, Set<SimpleParticipantType> participants, Date modified, String disclaimer) {
        this.uuid = new UUID(32, 16);
        this.participants = participants;
        recordItems = new CharacteristicsList(uuid.toString());
        recordItems.add(new Characteristic<>(uuid.toString() + "-Property", listingObject));
        recordItems.add(new Characteristic<>(uuid.toString() + "-Broker", broker));
        recordItems.add(new Characteristic<>(uuid.toString() + "-LastModified", modified));
        recordItems.add(new Characteristic<>(uuid.toString() + "-Disclaimer", disclaimer));
    }

    public Property getListingObject() {
        return (Property) recordItems.getByName(uuid.toString() + "-Property").getValue();
    }

    public void setListingObject(Property listingObject) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-Property", listingObject));
    }

    public BusinessType getBroker() {
        return (BusinessType) recordItems.getByName(uuid.toString() + "-Broker").getValue();
    }

    public void setBroker(BusinessType broker) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-Broker", broker));
    }

    public Set<SimpleParticipantType> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<SimpleParticipantType> participants) {
        this.participants = participants;
    }

    public void addParticipant(SimpleParticipantType participant) {
        this.participants.add(participant);
    }

    public void removeParticipant(SimpleParticipantType participant) {
        this.participants.remove(participant);
    }

    public Date getLastModified() {
        return (Date) recordItems.getByName(uuid.toString() + "-LastModified").getValue();
    }

    public void setLastModified(Date modified) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-LastModified", modified));
    }

    public String getDisclaimer() {
        return (String) recordItems.getByName(uuid.toString() + "-Disclaimer").getValue();
    }

    public void setDisclaimer(String disclaimer) {
        recordItems.add(new Characteristic<>(uuid.toString() + "-Disclaimer", disclaimer));
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
        if ((double) listing.getCharacteristics().getByName(listing.getName() + "-Price").getValue() >= HIGH_VALUE_LOWEST) {
            return true;
        }
        return false;
    }
}
