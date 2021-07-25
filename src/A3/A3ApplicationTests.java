package A3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CharacteristicTests {
    @Test
    void characteristicTypes() {
        //Integer test
        Characteristic<Integer> a = new Characteristic<>("a", 0);
        Assertions.assertEquals(a.getName(), "a");
        Assertions.assertEquals(a.getValue(), 0);
        Assertions.assertEquals(a.toString(), "(a:0)");
        //Double test
        Characteristic<Double> b = new Characteristic<>("b", 1.5);
        Assertions.assertEquals(b.getName(), "b");
        Assertions.assertEquals(b.getValue(), 1.5);
        Assertions.assertEquals(b.toString(), "(b:1.5)");
        //Boolean test
        Characteristic<Boolean> c = new Characteristic<>("c", false);
        Assertions.assertEquals(c.getName(), "c");
        Assertions.assertEquals(c.getValue(), false);
        Assertions.assertEquals(c.toString(), "(c:false)");
        //String test
        Characteristic<String> d = new Characteristic<>("d", "string!");
        Assertions.assertEquals(d.getName(), "d");
        Assertions.assertEquals(d.getValue(), "string!");
        Assertions.assertEquals(d.toString(), "(d:string!)");
    }

    @Test
    void characteristicEquals() {
        Characteristic<Double> b = new Characteristic<>("b", 12.5);
        Characteristic<Double> b2 = new Characteristic<>("b", 25.4); //same name different value
        Assertions.assertEquals(b, b2); //should be true, for the equals method on characteristics all we case about is the name, not the value
    }
}

class CharacteristicsListTests {
    @Test
    void characteristicListTypes() {
        CharacteristicsList a = new CharacteristicsList("a");
        a
                .add(new Characteristic<Integer>("b", 1))
                .add(new Characteristic<Double>("c", 1.5))
                .add(new Characteristic<Boolean>("d", true))
                .add(new Characteristic<String>("e", "string"));
        Assertions.assertEquals(a.getByName("b").getValue(), 1);
        Assertions.assertEquals(a.getByName("c").getValue(), 1.5);
        Assertions.assertEquals(a.getByName("d").getValue(), true);
        Assertions.assertEquals(a.getByName("e").getValue(), "string");
        Assertions.assertEquals(a.toString(), "a:[(b:1)(c:1.5)(d:true)(e:string)]");
    }

    @Test
    void characteristicListCleaning() {
        CharacteristicsList a = new CharacteristicsList("a");
        a
                .add(new Characteristic<Integer>("b", 1))
                .add(new Characteristic<Double>("c", 1.5))
                .add(new Characteristic<Boolean>("d", true))
                .add(new Characteristic<String>("e", null)); //this null value should be filtered out in the toString
        Assertions.assertEquals(a.toString(), "a:[(b:1)(c:1.5)(d:true)]");
    }

    @Test
    void characteristicListRemoval() {
        CharacteristicsList a = new CharacteristicsList("a");
        a
                .add(new Characteristic<Integer>("b", 1))
                .add(new Characteristic<Double>("c", 1.5))
                .add(new Characteristic<Boolean>("d", true))
                .add(new Characteristic<String>("e", "string"));
        a.remove(a.getByName("e"));
        Assertions.assertEquals(a.toString(), "a:[(b:1)(c:1.5)(d:true)]");
    }

    @Test
    void characteristicListUpdating() {
        CharacteristicsList a = new CharacteristicsList("a");
        a
                .add(new Characteristic<Integer>("b", 1))
                .add(new Characteristic<Double>("c", 1.5))
                .add(new Characteristic<Boolean>("d", true))
                .add(new Characteristic<String>("e", "string"));
        a.update(new Characteristic<String>("d", "now im a string!"));
        a.update(new Characteristic<Integer>("f", 100)); //this name does not exist, shouldn't do anything
        Assertions.assertEquals(a.toString(), "a:[(b:1)(c:1.5)(d:now im a string!)(e:string)]");
    }
}

class BlueprintsTests {
    @Test
    void blueprintsEmptyTest() {
        BuiltBlueprint a = new BuiltBlueprint("a");
        Assertions.assertEquals(a.buildBlueprint().toString(), "a:[]");
    }

    @Test
    void blueprintsSetTest() {
        BuiltBlueprint a = new BuiltBlueprint("a");
        a.setBasement(false);
        a.setAddress("address");
        a.setBathrooms(100);
        a.setLotSize(96.024);
        Assertions.assertEquals(a.buildBlueprint().toString(), "a:[(a-Address:address)(a-LotSize:96.024)(a-NumBathrooms:100)(a-HasBasement:false)]");
    }
}

class SimpleParticipantTypeTests{
    SimpleParticipantType a = new SimpleParticipantType("id1","first","middle","last","role1","132-123-1234","132-123-1235","email@email.ca","132-123-1236","website.ca");

    @Test
    void SimpleParticipantTypeGetters(){
        Assertions.assertEquals(a.getParticipantId(),"id1");
        Assertions.assertEquals(a.getFirstName(),"first");
        Assertions.assertEquals(a.getMiddleName(),"middle");
        Assertions.assertEquals(a.getLastName(),"last");
        Assertions.assertEquals(a.getRole(),"role1");
        Assertions.assertEquals(a.getPrimaryContactPhone(),"132-123-1234");
        Assertions.assertEquals(a.getOfficePhone(),"132-123-1235");
        Assertions.assertEquals(a.getEmail(),"email@email.ca");
        Assertions.assertEquals(a.getFax(),"132-123-1236");
        Assertions.assertEquals(a.getWebsiteURL(),"website.ca");
    }

    @Test
    void SimpleParticipantTypeSetters(){
        a.setParticipantId("a");
        a.setFirstName("b");
        a.setMiddleName("c");
        a.setLastName("d");
        a.setRole("e");
        a.setPrimaryContactPhone("f");
        a.setOfficePhone("g");
        a.setEmail("h");
        a.setFax("i");
        a.setWebsiteURL("j");
        Assertions.assertEquals(a.getParticipantId(),"a");
        Assertions.assertEquals(a.getFirstName(),"b");
        Assertions.assertEquals(a.getMiddleName(),"c");
        Assertions.assertEquals(a.getLastName(),"d");
        Assertions.assertEquals(a.getRole(),"e");
        Assertions.assertEquals(a.getPrimaryContactPhone(),"f");
        Assertions.assertEquals(a.getOfficePhone(),"g");
        Assertions.assertEquals(a.getEmail(),"h");
        Assertions.assertEquals(a.getFax(),"i");
        Assertions.assertEquals(a.getWebsiteURL(),"j");
    }
}

class PropertyTests {
    final double EPSILON = 0.000001;
    String name = "AlwaysThisName";
    double testArea = 69420.1337;
    BuiltBlueprint a = new BuiltBlueprint(name);
    UUID testUUID = UUID.randomUUID();
    EnumSet<Zoning> testZones = EnumSet.noneOf(Zoning.class);

    @Test
    void LandTest() throws MissingCharacteristicException {
        testZones.add(Zoning.RESIDENTIAL);

        a.setLandId(testUUID);
        a.setZone(testZones);
        assertThrows(MissingCharacteristicException.class, () -> new Land(name, a.buildBlueprint()));
        a.setFreehold(true);
        assertThrows(MissingCharacteristicException.class, () -> new Land(name, a.buildBlueprint()));
        a.setLotSize(testArea);
        Land b = new Land(name, a.buildBlueprint());
        assertEquals(testUUID, b.getLandId());
        assertEquals(testZones, b.getZone());
        assertEquals(true, b.getFreeholdable());
        assertEquals(testArea, b.getLotSize(), EPSILON);

        UUID newTestUUID = UUID.randomUUID();
        b.setLandId(newTestUUID);
        String test = testUUID.toString();
        String test2 = b.getLandId().toString();
        assertNotEquals(testUUID.toString(), b.getLandId().toString());

        b.setLotSize(3.0);
        assertNotEquals(testArea,b.getLotSize());

        EnumSet<Zoning> newTestZones = EnumSet.noneOf(Zoning.class);
        newTestZones.add(Zoning.COMMERCIAL);
        b.setZone(newTestZones);
        assertNotEquals(testZones, b.getZone());

        b.addZone(Zoning.RESIDENTIAL);
        assertEquals(2,b.getZone().size(),EPSILON);
        b.addZone(Zoning.RESIDENTIAL);
        assertEquals(2,b.getZone().size(),EPSILON); // size shouldn't change because we used set - can't have repeated values
        b.removeZone(Zoning.COMMERCIAL);
        assertEquals(1,b.getZone().size(),EPSILON);
    }

    @Test
    void ImmobileStructureTest() throws MissingCharacteristicException {
        a.setLandId(testUUID);
        a.setZone(testZones);
        a.setFreehold(false);
        a.setLotSize(testArea);
        a.setCanMove(false);
        a.setNewConstruct(true);
        a.setIsDetached(DetachedType.NOT_DETACHED);
        assertThrows(MissingCharacteristicException.class, () -> new ParkingSpace(name, a.buildBlueprint()));
        a.setNumParking(3);
        ParkingSpace p = new ParkingSpace(name,a.buildBlueprint());
        assertThrows(MissingCharacteristicException.class, () -> new Locker(name, a.buildBlueprint()));
        a.setSquareFootage(15.5);
        Locker b = new Locker(name,a.buildBlueprint());

        assertEquals(3,p.getNumParking());
        assertEquals(15.5,b.getLockerSize());

        p.setNumParking(4932);
        assertNotEquals(3,p.getNumParking());
        b.setLockerSize(231.09);
        assertNotEquals(15.5,b.getLockerSize());
    }
    /*
    @Test
    void HouseTest() throws MissingCharacteristicException {
        a.setLandId(testUUID);
        a.setZone(testZones);
        a.setFreehold(true);
        a.setLotSize(testArea);
        a.setCanMove(false);
        a.setNewConstruct(true);
        assertThrows(MissingCharacteristicException.class, () -> new House(name, a.buildBlueprint()));
        a.setIsDetached(DetachedType.DEFAULT);
        House b = new House(name, a.buildBlueprint());
        assertEquals(false,b.getMovable());
        assertEquals(true,b.getNewConstruct());
        assertEquals(DetachedType.DETACHED.getDescription(),b.getDetachable().getDescription());

        b.setMovable(true);
        b.setNewConstruct(false);
        b.setDetachable(DetachedType.SEMI_DETACHED);
        assertNotEquals(false,b.getMovable());
        assertNotEquals(true,b.getNewConstruct());
        assertNotEquals(DetachedType.DETACHED.getDescription(),b.getDetachable().getDescription());
    }
    */
}
