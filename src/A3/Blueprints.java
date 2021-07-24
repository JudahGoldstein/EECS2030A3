package A3;

import java.util.EnumSet;
import java.util.UUID;

/**
 * Blueprints is an abstract utility class which holds pre-made methods to build characteristics used by multiple aspects of an MLS record
 */
public abstract class Blueprints extends CharacteristicsList {
    private CharacteristicsList cleanCharacteristics;

    /**
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     */
    Blueprints(String name) {
        super(name);
        cleanCharacteristics = new CharacteristicsList(name);
    }

    /**
     * method that cleans up the working characteristic list
     *
     * @return characteristic list purged of all null values
     */
    CharacteristicsList build() {
        for (Characteristic<?> i : super.getCharacteristics()) {
            if (i.getValue() != null) {
                cleanCharacteristics.add(i);
            }
        }
        return cleanCharacteristics;
    }

    /**
     * these methods are "blueprints" that subclasses can make use of
     */
    void defaultAttributes(String address, double price, ListingCategory listingType, boolean isFreehold, String description) {
        super.add(new Characteristic<>(super.getName() + "-Address", address));
        super.add(new Characteristic<>(super.getName() + "-Price", price));
        super.add(new Characteristic<>(super.getName() + "-ListingType", listingType));
        super.add(new Characteristic<>(super.getName() + "-IsFreehold", isFreehold));
        super.add(new Characteristic<>(super.getName() + "-Description", description));
    }

    void landAttributes(UUID landId, EnumSet<Zoning> zone, double lotSize) {
        super.add(new Characteristic<>(super.getName() + "-LandId", landId));
        super.add(new Characteristic<>(super.getName() + "-Zoning", zone));
        super.add(new Characteristic<>(super.getName() + "-LotSize", lotSize));
    }

    void structureAttributes(boolean canMove, boolean isNewConstruct, DetachedType isDetached) {
        super.add(new Characteristic<>(super.getName() + "-Movable", canMove));
        super.add(new Characteristic<>(super.getName() + "-NewConstruction", isNewConstruct));
        super.add(new Characteristic<>(super.getName() + "-DetachedType", isDetached));
    }

    void livingUnitAttributes(boolean isCoOp, boolean isMultiFam, boolean isMultiGen) {
        super.add(new Characteristic<>(super.getName() + "-IsCoOpHousing", isCoOp));
        super.add(new Characteristic<>(super.getName() + "-MultiFamilyType", isMultiFam));
        super.add(new Characteristic<>(super.getName() + "-IsMultiGen", isMultiGen));
    }

    void typicalResidentialAttributes(double squareFootage, int floors, int constructionYear, int roomCount, int bedrooms, int bathrooms, int kitchens, boolean basement, boolean deck) {
        super.add(new Characteristic<>(super.getName() + "-LivingArea", squareFootage));
        super.add(new Characteristic<>(super.getName() + "-Floors", floors));
        super.add(new Characteristic<>(super.getName() + "-YearConstructed", constructionYear));
        super.add(new Characteristic<>(super.getName() + "-RoomCount", roomCount));
        super.add(new Characteristic<>(super.getName() + "-NumBedrooms", bedrooms));
        super.add(new Characteristic<>(super.getName() + "-NumBathrooms", bathrooms));
        super.add(new Characteristic<>(super.getName() + "-NumKitchens", kitchens));
        super.add(new Characteristic<>(super.getName() + "-HasBasement", basement));
        super.add(new Characteristic<>(super.getName() + "-HasDeck", deck));
    }

    void houseAttributes(boolean attic, boolean garden, boolean lawn) {
        super.add(new Characteristic<>(super.getName() + "-HasAttic", attic));
        super.add(new Characteristic<>(super.getName() + "-HasGarden", garden));
        super.add(new Characteristic<>(super.getName() + "-HasLawn", lawn));
    }
}

class BuiltBlueprint extends Blueprints {
    /**
     * variables the builder will use
     */
    String address, description;
    double price, lotSize, squareFootage;
    boolean isFreehold, canMove, isNewConstruct, isCoOp, isMultiFam, isMultiGen, attic, basement, deck, garden, lawn;
    int bedrooms, bathrooms, kitchens, floors, constructionYear, roomCount;
    UUID landId;
    EnumSet<Zoning> zone;
    DetachedType isDetached;
    ListingCategory listingType;

    /**
     * @param name required name of A3.Characteristic for constructor
     */
    BuiltBlueprint(String name) {
        super(name);
    }

    /**
     * setters to set the variables the builder will use
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLotSize(double lotSize) {
        this.lotSize = lotSize;
    }

    public void setSquareFootage(double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public void setFreehold(boolean freehold) {
        isFreehold = freehold;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setNewConstruct(boolean newConstruct) {
        isNewConstruct = newConstruct;
    }

    public void setCoOp(boolean coOp) {
        isCoOp = coOp;
    }

    public void setMultiFam(boolean multiFam) {
        isMultiFam = multiFam;
    }

    public void setMultiGen(boolean multiGen) {
        isMultiGen = multiGen;
    }

    public void setAttic(boolean attic) {
        this.attic = attic;
    }

    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    public void setDeck(boolean deck) {
        this.deck = deck;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setKitchens(int kitchens) {
        this.kitchens = kitchens;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public void setLandId(UUID landId) {
        this.landId = landId;
    }

    public void setZone(EnumSet<Zoning> zone) {
        this.zone = zone;
    }

    public void setIsDetached(DetachedType isDetached) {
        this.isDetached = isDetached;
    }

    public void setListingType(ListingCategory listingType) {
        this.listingType = listingType;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public void setLawn(boolean lawn) {
        this.lawn = lawn;
    }

    /**
     * runs the methods from the superclass with the variables set by the user, final because we do not want subclasses to inherit it
     *
     * @return clean characteristic list made by the superclass build function
     */
    final CharacteristicsList buildBlueprint() {
        defaultAttributes(address, price, listingType, isFreehold, description);
        landAttributes(landId, zone, lotSize);
        structureAttributes(canMove, isNewConstruct, isDetached);
        livingUnitAttributes(isCoOp, isMultiFam, isMultiGen);
        typicalResidentialAttributes(squareFootage, floors, constructionYear, roomCount, bedrooms, bathrooms, kitchens, basement, deck);
        houseAttributes(attic, garden, lawn);
        return build();
    }
}