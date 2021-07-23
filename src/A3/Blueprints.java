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

    CharacteristicsList build() {
        for (Characteristic<?> i : characteristics) {
            if (i.getValue() != null) {
                cleanCharacteristics.add(i);
            }
        }
        return cleanCharacteristics;
    }

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


    final CharacteristicsList buildHouse() {
        location(streetAddress, city, provinceOrState);
        pricing(listingPrice, commission, taxes);
        building(squareFootage, floors, constructionYear);
        residentialRooms(bedrooms, bathrooms, kitchens, attic, basement);
        return build();
    }
}

class apartmentComplex extends House{
    Boolean roofAccess;
    /**
     * @param name required name of A3.Characteristic for constructor
     */
    apartmentComplex(String name) {
        super(name);
    }

    public void setRoofAccess(Boolean roofAccess) {
        this.roofAccess = roofAccess;
    }

    @Override
    public void setAttic(boolean attic) {
        throw new UnsupportedOperationException();
    }

    @Override
    void residentialRooms(int bedrooms, int bathrooms, int kitchens, Boolean roofAccess, Boolean basement) {
        characteristics.add(new Characteristic<>(name + "-bedrooms-per-unit", bedrooms));
        characteristics.add(new Characteristic<>(name + "-bathrooms-per-unit", bathrooms));
        characteristics.add(new Characteristic<>(name + "-kitchens-per-unit", kitchens));
        characteristics.add(new Characteristic<>(name + "-roof-access", attic));
        characteristics.add(new Characteristic<>(name + "-complex-has-basement", basement));
    }

    final CharacteristicsList buildApartmentComplex() {
        location(streetAddress, city, provinceOrState);
        pricing(listingPrice, commission, taxes);
        building(squareFootage, floors, constructionYear);
        residentialRooms(bedrooms, bathrooms, kitchens, null, null);
        return build();
    }
}