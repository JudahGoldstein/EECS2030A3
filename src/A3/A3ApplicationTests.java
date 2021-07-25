package A3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    String address = "123 Apple Street";
    double testArea = 69420.1337;
    BuiltBlueprint a = new BuiltBlueprint(name);
    UUID testUUID = UUID.randomUUID();
    EnumSet<Zoning> testZones = EnumSet.noneOf(Zoning.class);

    @Test
    void LandTest() throws MissingCharacteristicException {
        testZones.add(Zoning.RESIDENTIAL);

        a.setAddress(address);
        a.setPrice(5678.90);
        a.setDescription("Test House");
        a.setListingType(ListingCategory.DEFAULT);
        a.setLandId(testUUID);
        a.setZone(testZones);
        assertThrows(MissingCharacteristicException.class, () -> new Land(name, a.buildBlueprint()));
        a.setFreehold(true);
        assertThrows(MissingCharacteristicException.class, () -> new Land(name, a.buildBlueprint()));
        a.setLotSize(testArea);
        Land b = new Land(name, a.buildBlueprint());

        assertEquals(address, b.getAddress());
        assertEquals(5678.90, b.getPrice(), EPSILON);
        assertEquals("Test House", b.getDescription());
        assertEquals(ListingCategory.PURCHASE.getDescription(), b.getListingType().getDescription());
        assertTrue(b.getFreeholdable());
        assertEquals(testUUID, b.getLandId());
        assertEquals(testZones, b.getZone());
        assertEquals(testArea, b.getLotSize(), EPSILON);

        b.setAddress("123 Pear Court");
        b.setPrice(123.21);
        b.setListingType(ListingCategory.RENT);
        b.setDescription("Clown House");
        b.setFreeholdable(false);

        assertNotEquals(address, b.getAddress());
        assertNotEquals(5678.90, b.getPrice(), EPSILON);
        assertNotEquals("Test House", b.getDescription());
        assertNotEquals(ListingCategory.PURCHASE.getDescription(), b.getListingType().getDescription());
        assertFalse(b.getFreeholdable());

        UUID newTestUUID = UUID.randomUUID();
        b.setLandId(newTestUUID);
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
        testZones.add(Zoning.COMMERCIAL);

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

        p.setMovable(true);
        assertFalse(p.getMovable()); // overridden method doesn't allow the Movable characteristic to be changed from false
    }

    @Test
    void CondominiumTest() throws MissingCharacteristicException {
        initLivingUnit();
        a.setIsDetached(DetachedType.NOT_DETACHED);
        assertThrows(MissingCharacteristicException.class, () -> new Condominium(name, a.buildBlueprint()));
        a.setCoOp(true);
        Condominium b = new Condominium(name, a.buildBlueprint());
        assertTrue(b.getZone().contains(Zoning.RESIDENTIAL)); // should be automatically added by the constructor of LivingUnit
        assertFalse(b.getMovable());
        assertTrue(b.getNewConstruct());
        assertEquals(DetachedType.NOT_DETACHED.getDescription(),b.getDetachable().getDescription());
        assertFalse(b.getMultiFamily());
        assertFalse(b.getMultiGen());
        assertTrue(b.getCoOp());

        b.setMovable(true);
        b.setNewConstruct(false);
        b.setDetachable(DetachedType.SEMI_DETACHED);
        b.setMultiFamily(true);
        b.setMultiGen(true);
        b.setCoOp(false);
        assertNotEquals(true,b.getMovable()); // overridden method doesn't allow the Movable characteristic to be changed from false
        assertNotEquals(true,b.getNewConstruct());
        assertNotEquals(DetachedType.NOT_DETACHED.getDescription(),b.getDetachable().getDescription());
        assertNotEquals(false,b.getMultiFamily());
        assertNotEquals(false,b.getMultiGen());
        assertNotEquals(true,b.getCoOp());
    }

    @Test
    void HouseTest() throws MissingCharacteristicException {
        initLivingUnit();
        House b = new House(name, a.buildBlueprint());
        assertEquals(false,b.getMovable());
        assertEquals(DetachedType.DETACHED.getDescription(),b.getDetachable().getDescription());

        b.setMovable(true);
        assertNotEquals(false,b.getMovable()); // this method is not overridden in House
    }

    @Test
    void ZonedHousingTest() throws MissingCharacteristicException {
        initLivingUnit();
        Farmhouse f = new Farmhouse(name, a.buildBlueprint());
        assertEquals(2,f.getZone().size(),EPSILON);
        assertTrue(f.getZone().contains(Zoning.AGRICULTURAL));
        assertFalse(f.getZone().contains(Zoning.RECREATIONAL));

        RecreationalHome r = new RecreationalHome(name, a.buildBlueprint());
        assertEquals(2,r.getZone().size(),EPSILON);
        assertTrue(f.getZone().contains(Zoning.RECREATIONAL));
        assertFalse(f.getZone().contains(Zoning.AGRICULTURAL));

        LiveWork w = new LiveWork(name, a.buildBlueprint());
        assertEquals(2,w.getZone().size(),EPSILON);
        assertTrue(f.getZone().contains(Zoning.COMMERCIAL));
        assertFalse(f.getZone().contains(Zoning.INDUSTRIAL));
    }

    private void initLivingUnit() {
        a.setAddress(address);
        a.setPrice(5678.90);
        a.setDescription("Test House");
        a.setListingType(ListingCategory.DEFAULT);
        a.setFreehold(true);
        a.setLandId(testUUID);
        a.setZone(testZones);
        a.setLotSize(testArea);
        a.setCanMove(false);
        a.setNewConstruct(true);
        a.setIsDetached(DetachedType.DEFAULT);
        a.setMultiFam(false);
        a.setMultiGen(false);
        a.setCoOp(false);
    }

    @Test
    void TownhouseTest() throws MissingCharacteristicException {
        initLivingUnit();
        Townhouse t = new Townhouse(name, a.buildBlueprint());
        StackedTownhouse s = new StackedTownhouse(name, a.buildBlueprint());
        TripleDecker d = new TripleDecker(name, a.buildBlueprint());

        t.setMovable(true);
        s.setMovable(true);
        d.setMovable(true);
        assertFalse(t.getMovable());
        assertFalse(s.getMovable());
        assertFalse(d.getMovable());
    }

    @Test
    void MultilexTest() throws MissingCharacteristicException {
        initLivingUnit();
        Multilex duplex = new Multilex(name, a.buildBlueprint(), MultilexType.DUPLEX);
        Multilex triplex = new Multilex(name, a.buildBlueprint(), MultilexType.TRIPLEX);
        Multilex quadruplex = new Multilex(name, a.buildBlueprint(), MultilexType.QUADRUPLEX);

        duplex.setMultiFamily(false);
        assertTrue(duplex.getMultiFamily()); // overridden method doesn't allow the isMultiFam characteristic to be changed from false

        assertEquals(MultilexType.DUPLEX.getDescription(),duplex.getMultiFamType().getDescription());
        assertEquals(MultilexType.TRIPLEX.getDescription(),triplex.getMultiFamType().getDescription());
        assertEquals(MultilexType.QUADRUPLEX.getDescription(),quadruplex.getMultiFamType().getDescription());
        assertNotEquals(MultilexType.DEFAULT.getDescription(),quadruplex.getMultiFamType().getDescription());

        Multilex testChange = duplex;
        testChange.setMultiFamType(MultilexType.QUADRUPLEX);
        assertEquals(quadruplex.getMultiFamType().getDescription(),testChange.getMultiFamType().getDescription());
    }

    @Test
    void addMoreCharacteristics() throws MissingCharacteristicException {
        initLivingUnit();
        House test = new House(name, a.buildBlueprint());
        Characteristic<Integer> z = new Characteristic<Integer>(name + "-RoomCount",9);
        Characteristic<Boolean> b = new Characteristic<Boolean>(name + "-HasAttic",true);
        Characteristic<Integer> c = new Characteristic<Integer>(name + "-NumBedrooms",3);
        Characteristic<Integer> d = new Characteristic<Integer>(name + "-NumBathrooms",2);
        Characteristic<Integer> e = new Characteristic<Integer>(name + "-NumKitchens",15);
        Characteristic<Integer> f = new Characteristic<Integer>(name + "-Floors",5);
        Characteristic<Integer> g = new Characteristic<Integer>(name + "-YearConstructed",1492);
        Characteristic<Boolean> h = new Characteristic<Boolean>(name + "-HasGarden",true);
        Characteristic<Boolean> i = new Characteristic<Boolean>(name + "-HasLawn",true);

        test.getCharacteristics().add(z);
        test.getCharacteristics().add(b);
        test.getCharacteristics().add(c);
        test.getCharacteristics().add(d);
        test.getCharacteristics().add(e);
        test.getCharacteristics().add(f);
        test.getCharacteristics().add(g);
        test.getCharacteristics().add(h);
        test.getCharacteristics().add(i);

        String result = test.getCharacteristics().toString();
        String compareWith =
                name + ":[(" + name + "-Address:" + address  + ")("
                             + name + "-Price:" + 5678.9 + ")("
                             + name + "-ListingType:DEFAULT)("
                             + name + "-IsFreehold:true)("
                             + name + "-Description:Test House)("
                             + name + "-LandId:" + testUUID.toString() + ")("
                             + name + "-Zoning:[RESIDENTIAL])("
                             + name + "-LotSize:" + 69420.1337 + ")("
                             + name + "-Movable:false)("
                             + name + "-NewConstruction:true)("
                             + name + "-DetachedType:DEFAULT)("
                             + name + "-IsCoOpHousing:false)("
                             + name + "-IsMultiFam:false)("
                             + name + "-IsMultiGen:false)("
                             + name + "-RoomCount:9)("
                             + name + "-HasAttic:true)("
                             + name + "-NumBedrooms:3)("
                             + name + "-NumBathrooms:2)("
                             + name + "-NumKitchens:15)("
                             + name + "-Floors:5)("
                             + name + "-YearConstructed:1492)("
                             + name + "-HasGarden:true)("
                             + name + "-HasLawn:true)]";

        assertEquals(compareWith, result);
    }
}

class MlsRecordTests {
    final double EPSILON = 0.000001;
    String name = "AlwaysThisName";
    String address = "123 Apple Street";
    double testArea = 69420.1337;
    BuiltBlueprint a = new BuiltBlueprint(name);
    UUID testUUID = UUID.randomUUID();
    EnumSet<Zoning> testZones = EnumSet.noneOf(Zoning.class);
    String disclaimer = "This may not reflect the most up-to-date information.";

    SimpleParticipantType p = new SimpleParticipantType("id1","first","middle","last","role1","132-123-1234","132-123-1235","email@email.ca","132-123-1236","website.ca");
    SimpleParticipantType q = new SimpleParticipantType("a", "b", "c", "d", "e","f", "g", "h","i","j");
    BusinessType broker = new BusinessType("a", "b", "c", "d", "e","f", "g", "h");
    BusinessType broker2 = new BusinessType("id1","first","middle","last","role1","132-123-1234","132-123-1235","email@email.ca");

    Set<SimpleParticipantType> people = new HashSet<SimpleParticipantType>();

    @Test
    void CreateOneRecord() throws MissingCharacteristicException {
        testZones.add(Zoning.RESIDENTIAL);
        people.add(p);

        a.setAddress(address);
        a.setPrice(5678.90);
        a.setDescription("Test House");
        a.setListingType(ListingCategory.DEFAULT);
        a.setFreehold(true);
        a.setLandId(testUUID);
        a.setZone(testZones);
        a.setLotSize(testArea);
        a.setCanMove(false);
        a.setNewConstruct(true);
        a.setIsDetached(DetachedType.DEFAULT);
        a.setMultiFam(false);
        a.setMultiGen(false);
        a.setCoOp(false);

        House h = new House(name, a.buildBlueprint());

        MlsRecord m = new MlsRecord(h, broker, people, new Date(), disclaimer);
        Date startTest = m.getLastModified();

        assertEquals(h.getCharacteristics().toString(), m.getListingObject().getCharacteristics().toString());
        assertEquals(broker.getName(),m.getBroker().getName());
        assertEquals(1, m.getParticipants().size());
        assertEquals(disclaimer,m.getDisclaimer());

        m.setBroker(broker2);
        m.addParticipant(q);
        assertEquals(2, m.getParticipants().size());
        m.addParticipant(q);
        assertEquals(2, m.getParticipants().size());
        m.removeParticipant(p);
        assertEquals(1, m.getParticipants().size());
        m.setDisclaimer("Friend");
        Date endTest = new Date();
        assertEquals(endTest.compareTo(startTest),m.getLastModified().compareTo(startTest),EPSILON);
        assertNotEquals(disclaimer,m.getDisclaimer());
        assertFalse(m.isHighValue());
        h.getCharacteristics().update(new Characteristic<>(h.getName() + "-Price",5000000.25));
        assertTrue(m.isHighValue());
    }
}

class MlsDatabaseTests {
    final double EPSILON = 0.000001;
    String name = "AlwaysThisName";
    String address = "123 Apple Street";
    double testArea = 69420.1337;
    BuiltBlueprint a = new BuiltBlueprint(name);
    UUID testUUID = UUID.randomUUID();
    EnumSet<Zoning> testZones = EnumSet.noneOf(Zoning.class);
    String disclaimer = "This may not reflect the most up-to-date information.";

    SimpleParticipantType p = new SimpleParticipantType("id1","first","middle","last","role1","132-123-1234","132-123-1235","email@email.ca","132-123-1236","website.ca");
    SimpleParticipantType q = new SimpleParticipantType("a", "b", "c", "d", "e","f", "g", "h","i","j");
    BusinessType broker = new BusinessType("a", "b", "c", "d", "e","f", "g", "h");
    BusinessType broker2 = new BusinessType("id1","first","middle","last","role1","132-123-1234","132-123-1235","email@email.ca");

    Set<SimpleParticipantType> people = new HashSet<SimpleParticipantType>();
    Set<SimpleParticipantType> people2 = new HashSet<SimpleParticipantType>();

    @Test
    void testDatabase() throws MissingCharacteristicException {
        testZones.add(Zoning.RESIDENTIAL);
        people.add(p);
        people2.add(q);

        a.setAddress(address);
        a.setPrice(5678.90);
        a.setDescription("Test House");
        a.setListingType(ListingCategory.DEFAULT);
        a.setFreehold(true);
        a.setLandId(testUUID);
        a.setZone(testZones);
        a.setLotSize(testArea);
        a.setCanMove(false);
        a.setNewConstruct(true);
        a.setIsDetached(DetachedType.DEFAULT);
        a.setMultiFam(false);
        a.setMultiGen(false);
        a.setCoOp(false);

        House h = new House(name, a.buildBlueprint());

        MlsRecord m = new MlsRecord(h, broker, people, new Date(), disclaimer);

        BuiltBlueprint b = new BuiltBlueprint("another");
        UUID testUUIDTwo = UUID.randomUUID();
        EnumSet<Zoning> testZonesTwo = EnumSet.noneOf(Zoning.class);
        testZonesTwo.add(Zoning.RESIDENTIAL);

        b.setAddress(address);
        b.setPrice(5000000.34);
        b.setDescription("Another Test House");
        b.setListingType(ListingCategory.LEASE);
        b.setFreehold(false);
        b.setLandId(testUUIDTwo);
        b.setZone(testZonesTwo);
        b.setLotSize(56034.2);
        b.setCanMove(false);
        b.setNewConstruct(true);
        b.setIsDetached(DetachedType.NOT_DETACHED);
        b.setNumParking(3);

        ParkingSpace p = new ParkingSpace("another",b.buildBlueprint());

        MlsRecord n = new MlsRecord(p, broker2, people, new Date(), disclaimer);

        MlsDatabase d = MlsDatabase.getInstance();

        assertEquals(0,d.getSize(), EPSILON);

        d.addListing(m.getUUID(), m);

        assertEquals(1,d.getSize(), EPSILON);

        d.addListing(n.getUUID(), n);

        assertEquals(2, d.getSize(), EPSILON);

        b.setSquareFootage(67.0);

        Locker l = new Locker("another",b.buildBlueprint());

        n.setListingObject(l);

        d.updateListing(n.getUUID(),n);

        assertEquals(2, d.getSize(), EPSILON);

        assertEquals(m.getDisclaimer(),d.searchDatabase(m.getUUID()).getDisclaimer());

        d.deleteListing(m.getUUID());

        assertEquals(1, d.getSize(), EPSILON);

        assertEquals("All information held in the database may not reflect the most up-to-date information.",MlsDatabase.getCollectionDisclaimer());
    }
}