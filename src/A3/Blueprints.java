package A3;

/**
 * Blueprints is an abstract utility class which holds pre-made methods to build characteristics used by multiple aspects of an MLS record
 */
public abstract class Blueprints extends CharacteristicsList {
    CharacteristicsList cleanCharacteristics;

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
        for (Characteristic<?> i : characteristics) {
            if (i.getValue() != null) {
                cleanCharacteristics.add(i);
            }
        }
        return cleanCharacteristics;
    }

    /**
     * these methods are "blueprints" that subclasses can make use of
     */
    void location(String streetAddress, String city, String provinceOrState) {
        characteristics.add(new Characteristic<>(name + "-street-address", streetAddress));
        characteristics.add(new Characteristic<>(name + "-city", city));
        characteristics.add(new Characteristic<>(name + "-province-or-state", provinceOrState));
    }

    void pricing(Double listingPrice, Double commission, Double taxes) {
        characteristics.add(new Characteristic<>(name + "-listing-price", listingPrice));
        characteristics.add(new Characteristic<>(name + "-commission", commission));
        characteristics.add(new Characteristic<>(name + "-taxes", taxes));
    }

    void building(Double squareFootage, int floors, int constructionYear) {
        characteristics.add(new Characteristic<>(name + "-square-footage", squareFootage));
        characteristics.add(new Characteristic<>(name + "-floors", floors));
        characteristics.add(new Characteristic<>(name + "-construction-year", constructionYear));
    }

    void residentialRooms(int bedrooms, int bathrooms, int kitchens, Boolean attic, Boolean basement) {
        characteristics.add(new Characteristic<>(name + "-bedrooms", bedrooms));
        characteristics.add(new Characteristic<>(name + "-bathrooms", bathrooms));
        characteristics.add(new Characteristic<>(name + "-kitchens", kitchens));
        characteristics.add(new Characteristic<>(name + "-has-attic", attic));
        characteristics.add(new Characteristic<>(name + "-has-basement", basement));
    }
}

class House extends Blueprints {
    /**
     * variables the builder will use
     */
    String streetAddress, city, provinceOrState;
    Double listingPrice, commission, taxes, squareFootage;
    Boolean attic, basement;
    int bedrooms, bathrooms, kitchens, floors, constructionYear;

    /**
     * @param name required name of A3.Characteristic for constructor
     */
    House(String name) {
        super(name);
    }

    /**
     * setters to set the variables the builder will use
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvinceOrState(String provinceOrState) {
        this.provinceOrState = provinceOrState;
    }

    public void setListingPrice(Double listingPrice) {
        this.listingPrice = listingPrice;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public void setSquareFootage(Double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public void setAttic(boolean attic) {
        this.attic = attic;
    }

    public void setBasement(boolean basement) {
        this.basement = basement;
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

    /**
     * runs the methods from the superclass with the variables set by the user, final because we do not want subclasses to inherit it
     *
     * @return clean characteristic list made by the superclass build function
     */
    final CharacteristicsList buildHouse() {
        location(streetAddress, city, provinceOrState);
        pricing(listingPrice, commission, taxes);
        building(squareFootage, floors, constructionYear);
        residentialRooms(bedrooms, bathrooms, kitchens, attic, basement);
        return build();
    }
}

class apartmentComplex extends House {
    /**
     * extra variable the builder will use not included in house
     */
    Boolean roofAccess;

    /**
     * @param name required name of A3.Characteristic for constructor
     */
    apartmentComplex(String name) {
        super(name);
    }

    /**
     * setter for roofAccess
     *
     * @param roofAccess weather or not the apartment complex has roof access
     */
    public void setRoofAccess(Boolean roofAccess) {
        this.roofAccess = roofAccess;
    }

    /**
     * roof access replaces attic in apartment complex, rather than make a whole new blueprint for this small change we can throw an exception if someone accidentally tries to set it.
     */
    @Override
    public void setAttic(boolean attic) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param roofAccess replaces attic
     */
    @Override
    void residentialRooms(int bedrooms, int bathrooms, int kitchens, Boolean roofAccess, Boolean basement) {
        characteristics.add(new Characteristic<>(name + "-bedrooms-per-unit", bedrooms));
        characteristics.add(new Characteristic<>(name + "-bathrooms-per-unit", bathrooms));
        characteristics.add(new Characteristic<>(name + "-kitchens-per-unit", kitchens));
        characteristics.add(new Characteristic<>(name + "-roof-access", roofAccess));
        characteristics.add(new Characteristic<>(name + "-complex-has-basement", basement));
    }

    /**
     * runs the methods from the superclass with the variables set by the user, final because we do not want subclasses to inherit it
     *
     * @return clean characteristic list made by the superclass build function
     */
    final CharacteristicsList buildApartmentComplex() {
        location(streetAddress, city, provinceOrState);
        pricing(listingPrice, commission, taxes);
        building(squareFootage, floors, constructionYear);
        residentialRooms(bedrooms, bathrooms, kitchens, roofAccess, basement);
        return build();
    }
}