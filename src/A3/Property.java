package A3;

import java.util.EnumSet;
import java.util.UUID;

public abstract class Property implements Freeholdable {
    private CharacteristicsList characteristics;
    private String name;

    /**
     * @param name required name of A3.Characteristic for constructor and a cleaned version of the characteristic list to output
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

    public CharacteristicsList getCharacteristics() {
        return this.characteristics;
    }

    public String getName() {
        return this.name;
    }

    public boolean getFreeholdable() {
        return (boolean) characteristics.getByName(name + "-IsFreehold").getValue();
    }

    public void setFreeholdable(boolean isFreehold) {
        this.characteristics.add(new Characteristic<>(name + "-IsFreehold", isFreehold));
    }
}

class Land extends Property {
    // using an EnumSet for zoning makes this code compatible with multi-use zoning policies (such as that in Baltimore) https://sustainablecitycode.org/brief/mixed-use-zoning/

    public Land(String name, CharacteristicsList characteristics) {
        super(name,characteristics);
    }

    public UUID getLandId() {
        return (UUID) super.getCharacteristics().getByName(super.getName() + "-LandId").getValue();
    }

    public void setLandId(UUID uuid) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-LandId", uuid));
    }

    public EnumSet<Zoning> getZone() {
        return (EnumSet<Zoning>) super.getCharacteristics().getByName(super.getName() + "-Zoning").getValue();
    }

    public void setZone(EnumSet<Zoning> zone) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-Zoning", zone));
    }

    public void addZone(Zoning newZone) {
        this.getZone().add(newZone);
    }

    public void removeZone(Zoning remZone) {
        this.getZone().remove(remZone);
    }
}

class Structure extends Land implements Movable, NewConstructable, Detachable {

    public Structure (String name, CharacteristicsList characteristics) {
        super(name, characteristics);

    }

    public boolean getMovable() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-Movable").getValue();
    }

    public DetachedType getDetachable() {
        return (DetachedType) super.getCharacteristics().getByName(super.getName() + "-DetachedType").getValue();
    }

    public void setMovable(boolean canMove) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-Movable", canMove));
    }

    public void setDetachable(DetachedType isDetached) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-DetachedType", isDetached));
    }

    public boolean getNewConstruct() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-NewConstruction").getValue();
    }

    public void setNewConstruct(boolean newConstruct) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-NewConstruction", newConstruct));
    }
}

class ImmobileStructure extends Structure {
    public ImmobileStructure (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.setMovable(false);
        super.setDetachable(DetachedType.NOT_DETACHED);
    }

    @Override
    public void setMovable(boolean canMove) {
    }

    @Override
    public void setDetachable(DetachedType isDetached) {
    }
}

class ParkingSpace extends ImmobileStructure {
    public ParkingSpace (String name, CharacteristicsList characteristics, int numParkingSpaces) {
        super(name, characteristics);
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-NumParkingSpaces", numParkingSpaces));
    }
}

class Locker extends ImmobileStructure {
    public Locker (String name, CharacteristicsList characteristics, double size) {
        super(name, characteristics);
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-LockerSize", size));
    }
}

class LivingUnit extends Structure implements Cooperable, MultiFamiliable, Multigenerationalable {

    public LivingUnit (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }

    public boolean getMultiFamily() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-MultiFamilyType").getValue();
    }

    public void setMultiFamily(boolean isMultiFam) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-MultiFamilyType", isMultiFam));
    }

    public void setCoOp(boolean isCoOp) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-IsCoOpHousing", isCoOp));
    }

    public boolean getCoOp() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-IsCoOpHousing").getValue();
    }

    public boolean getMultiGen() {
        return (boolean) super.getCharacteristics().getByName(super.getName() + "-IsMultiGen").getValue();
    }

    public void setMultiGen(boolean isMultiGen) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-IsMultiGen", isMultiGen));
    }
}

class Condominium extends LivingUnit {
    public Condominium (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.setMovable(false);
        super.setDetachable(DetachedType.NOT_DETACHED);
    }

    @Override
    public void setMovable(boolean canMove) {
    }

    @Override
    public void setDetachable(DetachedType isDetached) {
    }
}

class House extends LivingUnit {
    public House (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class Farmhouse extends House {
    public Farmhouse (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.addZone(Zoning.AGRICULTURAL);
    }
}

class Townhouse extends House {
    public Townhouse (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
        super.setMovable(false);
        super.setDetachable(DetachedType.NOT_DETACHED);
    }

    @Override
    public void setMovable(boolean canMove) {
    }

    @Override
    public void setDetachable(DetachedType isDetached) {
    }
}

class StackedTownhouse extends Townhouse {
    public StackedTownhouse (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class TripleDecker extends StackedTownhouse {
    public TripleDecker (String name, CharacteristicsList characteristics) {
        super(name, characteristics);
    }
}

class Multilex extends House {
    // by definition they are multi-family homes https://www.chicotsky.com/services/residential/residential-duplexes-triplex-and-fourplexes

    public Multilex(String name, CharacteristicsList characteristics, MultilexType multiFamType) {
        super(name, characteristics);
        super.setMovable(false);
        super.setDetachable(DetachedType.NOT_DETACHED);
        super.setMultiFamily(true);
        this.setMultiFamType(multiFamType);
    }

    @Override
    public void setMultiFamily(boolean isMultiFam) {

    }

    @Override
    public void setMovable(boolean canMove) {
    }

    @Override
    public void setDetachable(DetachedType isDetached) {
    }

    public MultilexType getMultiFamType() {
        return (MultilexType) super.getCharacteristics().getByName(super.getName() + "-MultiFamilyType").getValue();
    }

    public void setMultiFamType(MultilexType multiFamType) {
        super.getCharacteristics().add(new Characteristic<>(super.getName() + "-MultiFamilyType", multiFamType));
    }
}