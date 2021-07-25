package A3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
