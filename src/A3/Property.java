package A3;

import java.util.EnumSet;
import java.util.UUID;

public abstract class Property implements Freeholdable {
    private CharacteristicsList characteristics;
    private String name;

    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    Property (String name, CharacteristicsList characteristics) {
        this.name = name;
        this.characteristics = new CharacteristicsList(name);
        for (Characteristic<?> i : characteristics.getCharacteristics()) {
            if (i.getValue() != null) {
                this.characteristics.add(i);
            }
        }
    }

    /**
     * Get the CharacteristicsList of this property
     * @return the CharacteristicsList of this property
     */
    public CharacteristicsList getCharacteristics() {
        return this.characteristics;
    }

    /**
     * Get the name associated with the Property
     * @return the name associated with the Property
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get whether or not this Property is a freehold
     * @return whether or not this Property is a freehold
     */
    public Boolean getFreeholdable() {
        return (Boolean) characteristics.getByName(name + "-IsFreehold").getValue();
    }

    /**
     * Set whether or not this Property is a freehold
     * @param isFreehold whether or not this Property is a freehold
     */
    public void setFreeholdable(Boolean isFreehold) {
        this.characteristics.add(new Characteristic<>(name + "-IsFreehold", isFreehold));
    }
}

class Land extends Property {
    // using an EnumSet for zoning makes this code compatible with multi-use zoning policies (such as that in Baltimore) https://sustainablecitycode.org/brief/mixed-use-zoning/

    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public Land(String name, CharacteristicsList characteristics) {
        super(name,characteristics);
    }

    /**
     * Get the UUID of the land
     * @return the UUID of the land
     */
    public UUID getLandId() {
        return (UUID) super.getCharacteristics().getByName(super.getName() + "-LandId").getValue();
    }

    /**
     * Set the UUID characteristic of the land
     * @param uuid the UUID to be set
     */
    public void setLandId(UUID uuid) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-LandId", uuid));
    }

    /**
     * Get the zoning of the land
     * @return the zoning(s) that apply to the land
     */
    public EnumSet<Zoning> getZone() {
        EnumSet<Zoning> result = (EnumSet<Zoning>) super.getCharacteristics().getByName(super.getName() + "-Zoning").getValue();
        if (result == null) {
            // https://www.baeldung.com/java-enumset
            result = EnumSet.noneOf(Zoning.class);
            super.getCharacteristics().add(new Characteristic<>(super.getName() + "-Zoning", result));
        }
        return result;
    }

    /**
     * Set the zoning(s) that apply to the land
     * @param zone the zoning(s) that apply to the land
     */
    public void setZone(EnumSet<Zoning> zone) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-Zoning", zone));
    }

    /**
     * Apply a zoning category to the land's existing zoning
     * @param newZone the zoning category to be added
     */
    public void addZone(Zoning newZone) {
        this.getZone().add(newZone);
    }

    /**
     * Remove a zoning category from the land's existing zoning
     * @param remZone the zoning category to be remove
     */
    public void removeZone(Zoning remZone) {
        this.getZone().remove(remZone);
    }

    /**
     * Get the size of the lot
     * @return the size of the lot
     */
    public Double getLotSize() {
        return (Double) super.getCharacteristics().getByName(super.getName() + "-LotSize").getValue();
    }

    /**
     * Set the size of the lot
     * @param area the size of the lot
     */
    public void setLotSize(Double area) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-LotSize", area));
    }
}

class Structure extends Land implements Movable, NewConstructable, Detachable {

    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public Structure (String name, CharacteristicsList characteristics) {
        super(name, characteristics);

    }

    /**
     * Get whether or not the Structure can be moved
     * @return whether or not the Structure can be moved
     */
    public Boolean getMovable() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-Movable").getValue();
    }

    /**
     * Get whether the Structure is detached, or semi-detached, or undetatched. Default value is detached.
     * @return an enum describing if it's detached
     */
    public DetachedType getDetachable() {
        return (DetachedType) super.getCharacteristics().getByName(super.getName() + "-DetachedType").getValue();
    }

    /**
     * Set whether or not the Structure can be moved
     * @param canMove whether or not the Structure can be moved
     */
    public void setMovable(Boolean canMove) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-Movable", canMove));
    }

    /**
     * Set whether the Structure is detached, semi-detached, or undetached.
     * @param isDetached An enum indicating one of the three kinds of detachment
     */
    public void setDetachable(DetachedType isDetached) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-DetachedType", isDetached));
    }

    /**
     * Get whether this Structure is newly constructed or not
     * @return whether this Structure is newly constructed or not
     */
    public Boolean getNewConstruct() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-NewConstruction").getValue();
    }

    /**
     * Set whether this Structure is newly constructed or not
     * @param newConstruct whether this Structure is newly constructed or not
     */
    public void setNewConstruct(Boolean newConstruct) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-NewConstruction", newConstruct));
    }
}

class ImmobileStructure extends Structure {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public ImmobileStructure (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.setMovable(false);
    }

    /**
     * Immobile structures can never move
     * @param canMove whether or not the Structure can be moved
     */
    @Override
    public void setMovable(Boolean canMove) {
    }
}

class ParkingSpace extends ImmobileStructure {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public ParkingSpace (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class Locker extends ImmobileStructure {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public Locker (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class LivingUnit extends Structure implements Cooperable, MultiFamiliable, Multigenerationalable {

    /**
     * All LivingUnits are automatically zoned as Residential
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public LivingUnit (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.addZone(Zoning.RESIDENTIAL);
    }

    /**
     * Get whether or not this LivingUnit is a Multi-Family living unit
     * @return whether or not this LivingUnit is a Multi-Family living unit
     */
    public Boolean getMultiFamily() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-MultiFamilyType").getValue();
    }

    /**
     * Set whether or not this LivingUnit is a Multi-Family living unit
     * @param isMultiFam whether or not this LivingUnit is a Multi-Family living unit
     */
    public void setMultiFamily(Boolean isMultiFam) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-MultiFamilyType", isMultiFam));
    }

    /**
     * Set whether or not this LivingUnit is co-operative housing
     * @param isCoOp whether or not this LivingUnit is co-operative housing
     */
    public void setCoOp(Boolean isCoOp) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-IsCoOpHousing", isCoOp));
    }

    /**
     * Get whether or not this LivingUnit is co-operative housing
     * @return whether or not this LivingUnit is co-operative housing
     */
    public Boolean getCoOp() {
        return (Boolean) super.getCharacteristics().getByName(super.getName() + "-IsCoOpHousing").getValue();
    }

    /**
     * Get whether or not this LivingUnit is a multi-generational home
     * @return whether or not this LivingUnit is a multi-generational home
     */
    public Boolean getMultiGen() {
        return (Boolean) super.getCharacteristics().getByName(super.getName() + "-IsMultiGen").getValue();
    }

    /**
     * Set whether or not this LivingUnit is a multi-generational home
     * @param isMultiGen whether or not this LivingUnit is a multi-generational home
     */
    public void setMultiGen(Boolean isMultiGen) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-IsMultiGen", isMultiGen));
    }
}

class Condominium extends LivingUnit {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public Condominium (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.setMovable(false);
    }

    /**
     * Condominium units cannot be moved
     * @param canMove whether or not the Structure can be moved
     */
    @Override
    public void setMovable(Boolean canMove) {
    }
}

class House extends LivingUnit {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public House (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class Farmhouse extends House {
    /**
     * A farmhouse will automatically get the Agricultural zoning
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public Farmhouse (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.addZone(Zoning.AGRICULTURAL);
    }
}

class RecreationalHome extends House {
    /**
     * A recreational home will automatically get the Recreational zoning
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public RecreationalHome (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.addZone(Zoning.RECREATIONAL);
    }
}

class LiveWork extends House {
    /**
     * A live-work home will automatically get the Commercial zoning
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public LiveWork (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.addZone(Zoning.COMMERCIAL);
    }
}

class Townhouse extends House {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public Townhouse (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.setMovable(false);
    }

    /**
     * Townhouse units can never be moved
     * @param canMove whether or not the Structure can be moved
     */
    @Override
    public void setMovable(Boolean canMove) {
    }
}

class StackedTownhouse extends Townhouse {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public StackedTownhouse (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class TripleDecker extends StackedTownhouse {
    /**
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     */
    public TripleDecker (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class Multilex extends House {
    // by definition they are multi-family homes https://www.chicotsky.com/services/residential/residential-duplexes-triplex-and-fourplexes

    /**
     * Multilexes can have three different types: duplex, triplex, or quadruplex
     *
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
     * @param characteristics the CharacteristicsList corresponding to the Property
     * @param multiFamType the type of multilex the property is
     */
    public Multilex(String name, CharacteristicsList characteristics, MultilexType multiFamType) {
        super(name, characteristics);
        super.setMovable(false);
        super.setMultiFamily(true);
        this.setMultiFamType(multiFamType);
    }

    /**
     * By definition, multilexes are multi-family homes, therefore the multi-family boolean characteristic should not be changed
     *
     * @param isMultiFam whether or not this LivingUnit is a Multi-Family living unit
     */
    @Override
    public void setMultiFamily(Boolean isMultiFam) {

    }

    /**
     * Multilex units can never be moved
     * @param canMove whether or not the Structure can be moved
     */
    @Override
    public void setMovable(Boolean canMove) {
    }


    /**
     * Get the type of multilex
     * @return the type of multilex
     */
    public MultilexType getMultiFamType() {
        return (MultilexType) super.getCharacteristics().getByName(super.getName() + "-MultiFamilyType").getValue();
    }

    /**
     * Set the type of multilex
     * @param multiFamType the type of multilex: duplex, triplex, or quadruplex
     */
    public void setMultiFamType(MultilexType multiFamType) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-MultiFamilyType", multiFamType));
    }
}