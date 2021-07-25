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


    //these methods are "blueprints" that subclasses can make use of

    /**
     * Default attributes that the Property class should have
     *
     * @param address     A String containing the Property's address
     * @param price       The listing price
     * @param listingType What kind of listing this is: Purchase, Lease, or Rent
     * @param isFreehold  Whether or not the Property is deeded as freehold
     * @param description A description of the Property
     */
    void defaultAttributes(String address, Double price, ListingCategory listingType, Boolean isFreehold, String description) {
        super.add(new Characteristic<>(super.getName() + "-Address", address));
        super.add(new Characteristic<>(super.getName() + "-Price", price));
        super.add(new Characteristic<>(super.getName() + "-ListingType", listingType));
        super.add(new Characteristic<>(super.getName() + "-IsFreehold", isFreehold));
        super.add(new Characteristic<>(super.getName() + "-Description", description));
    }

    /**
     * Attributes that Land should have
     *
     * @param landId  A UUID identifying the parcel of land
     * @param zone    The zone type(s) that apply to the land
     * @param lotSize The size of the land
     */
    void landAttributes(UUID landId, EnumSet<Zoning> zone, Double lotSize) {
        super.add(new Characteristic<>(super.getName() + "-LandId", landId));
        super.add(new Characteristic<>(super.getName() + "-Zoning", zone));
        super.add(new Characteristic<>(super.getName() + "-LotSize", lotSize));
    }

    /**
     * Attributes that a Structure should have
     *
     * @param canMove        Whether or not the structure can be moved
     * @param isNewConstruct Whether or not the structure is newly constructed
     * @param isDetached     The type of detachment the house may have: Detached, Semi-Detached, or Undetached
     */
    void structureAttributes(Boolean canMove, Boolean isNewConstruct, DetachedType isDetached) {
        super.add(new Characteristic<>(super.getName() + "-Movable", canMove));
        super.add(new Characteristic<>(super.getName() + "-NewConstruction", isNewConstruct));
        super.add(new Characteristic<>(super.getName() + "-DetachedType", isDetached));
    }

    /**
     * Attributes that a LivingUnit should have
     *
     * @param isCoOp     Whether or not the LivingUnit is co-operative housing
     * @param isMultiFam Whether or not the LivingUnit is a multi-family home
     * @param isMultiGen Whether or not the LivingUnit is a multi-generational home
     */
    void livingUnitAttributes(Boolean isCoOp, Boolean isMultiFam, Boolean isMultiGen) {
        super.add(new Characteristic<>(super.getName() + "-IsCoOpHousing", isCoOp));
        super.add(new Characteristic<>(super.getName() + "-IsMultiFam", isMultiFam));
        super.add(new Characteristic<>(super.getName() + "-IsMultiGen", isMultiGen));
    }

    /**
     * Typical attributes that a LivingUnit could have
     *
     * @param squareFootage    The amount of floor space in the unit
     * @param floors           The number of floors in the unit
     * @param constructionYear The year the unit was constructed
     * @param roomCount        The number of rooms in the unit
     * @param bedrooms         The number of bedrooms in the unit
     * @param bathrooms        The number of bathrooms in the unit
     * @param kitchens         The number of kitchens in the unit
     * @param basement         Whether or not the unit has a basement
     * @param deck             Whether or not the unit has a deck
     */
    void typicalResidentialAttributes(Double squareFootage, Integer floors, Integer constructionYear, Integer roomCount, Integer bedrooms, Integer bathrooms, Integer kitchens, Boolean basement, Boolean deck) {
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

    /**
     * Typical attributes that a House could have
     *
     * @param attic  Whether or not the house has an attic
     * @param garden Whether or not the house has a garden
     * @param lawn   Whether or not the house has a lawn
     */
    void houseAttributes(Boolean attic, Boolean garden, Boolean lawn) {
        super.add(new Characteristic<>(super.getName() + "-HasAttic", attic));
        super.add(new Characteristic<>(super.getName() + "-HasGarden", garden));
        super.add(new Characteristic<>(super.getName() + "-HasLawn", lawn));
    }

    /**
     * Attributes that a parking space should have
     *
     * @param numParking number of parking spaces
     */
    void parkingAttribute(Integer numParking) {
        super.add(new Characteristic<>(super.getName() + "-NumParkingSpaces", numParking));
    }

    /**
     * Attributes that a storage locker should have
     *
     * @param size the size of the locker
     */
    void lockerAttribute(Double size) {
        super.add(new Characteristic<>(super.getName() + "-LockerSize", size));
    }
}

class BuiltBlueprint extends Blueprints {
    /**
     * variables the builder will use
     */
    String address, description;
    Double price, lotSize, squareFootage;
    Boolean isFreehold, canMove, isNewConstruct, isCoOp, isMultiFam, isMultiGen, attic, basement, deck, garden, lawn;
    Integer bedrooms, bathrooms, kitchens, floors, constructionYear, roomCount, numParking;
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
    }

    public void setSquareFootage(Double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public void setFreehold(Boolean freehold) {
        isFreehold = freehold;
    }

    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }

    public void setNewConstruct(Boolean newConstruct) {
        isNewConstruct = newConstruct;
    }

    public void setCoOp(Boolean coOp) {
        isCoOp = coOp;
    }

    public void setMultiFam(Boolean multiFam) {
        isMultiFam = multiFam;
    }

    public void setMultiGen(Boolean multiGen) {
        isMultiGen = multiGen;
    }

    public void setAttic(Boolean attic) {
        this.attic = attic;
    }

    public void setBasement(Boolean basement) {
        this.basement = basement;
    }

    public void setDeck(Boolean deck) {
        this.deck = deck;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setKitchens(Integer kitchens) {
        this.kitchens = kitchens;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public void setRoomCount(Integer roomCount) {
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

    public void setGarden(Boolean garden) {
        this.garden = garden;
    }

    public void setLawn(Boolean lawn) {
        this.lawn = lawn;
    }

    public void setNumParking(Integer numParking) {
        this.numParking = numParking;
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
        parkingAttribute(numParking);
        lockerAttribute(squareFootage);
        return build();
    }
}