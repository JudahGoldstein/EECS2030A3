import java.util.ArrayList;

/**
 * A collection of characteristics along with supporting code
 */
public class CharacteristicsList {
    int ListingNumber;
    ArrayList<Characteristic<?>> characteristics = new ArrayList<>();

    /**
     * @param ListingNumber required name of Characteristic for constructor
     */
    CharacteristicsList(int ListingNumber) {
        this.ListingNumber = ListingNumber;
    }

    /**
     * @param c characteristic to be added, will only add it if it has a unique name not in the set already
     * @return Type for fluid interface
     */
    public CharacteristicsList add(Characteristic<?> c) {
        for (Characteristic<?> i : characteristics) {
            if (c.equals(i)) {
                return this;
            }
        }
        characteristics.add(c);
        return this;
    }

    public void remove(Characteristic<?> c) {
        characteristics.remove(c);
    }

    public ArrayList<Characteristic<?>> getCharacteristics() {
        return characteristics;
    }

    /**
     * @param name name of characteristic to look for
     * @return characteristic with name if found, otherwise null
     */
    public Characteristic<?> getByName(String name) {
        for (Characteristic<?> i : characteristics) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    /**
     * used to copy characteristics of from another type into this one. useful for cases such as premaking types that can be applied to multiple types of buildings/structures
     * @param t the Type that all the characteristics will be cloned from
     */
    void copyList(CharacteristicsList t) {
        characteristics.addAll(t.characteristics);
    }

    /**
     *
     * @return string repersentation of the characteristics with non-null values
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(ListingNumber+":[");
        for (Characteristic<?> i : characteristics) {
            if (i.getValue() != null) {
                result.append(i);
            }
        }
        return result + "]";
    }
}
