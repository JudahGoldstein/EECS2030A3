package A3;

import java.util.ArrayList;

/**
 * A collection of characteristics along with supporting code
 */
public class CharacteristicsList {
    private ArrayList<Characteristic<?>> characteristics = new ArrayList<>();
    private String name;
    public static final int MAX_LIST_SIZE = 1000;

    /**
     * @param name required name of A3.Characteristic for constructor
     */
    CharacteristicsList(String name) {
        this.name = name;
    }

    /**
     * This method is used for adding Characteristics to the list
     *
     * @param c characteristic to be added, will only add it if it has a unique name not in the set already
     *          and if there is a characteristic with the same name, then overwrite the old value with the new one.
     * @return Type for fluid interface
     */
    public CharacteristicsList add(Characteristic<?> c) {
        if (characteristics.size() < MAX_LIST_SIZE) {
            for (Characteristic<?> i : characteristics) {
                if (c.equals(i)) {
                    int ind = characteristics.indexOf(i);
                    this.remove(i);
                    characteristics.add(ind, c); // take advantage of method overload in ArrayList
                    return this;
                }
            }
            characteristics.add(c);
            return this;
        }
        return this;
    }

    /**
     * Update a record in the characteristics list.
     *
     * @param c characteristic to be updated, will only update the value if there is a characteristic with the same name.
     * @return Type for fluid interface
     */
    public CharacteristicsList update(Characteristic<?> c) {
        for (Characteristic<?> i : characteristics) {
            if (c.equals(i)) {
                int ind = characteristics.indexOf(i);
                this.remove(i);
                characteristics.add(ind, c); // take advantage of method overload in ArrayList
                return this;
            }
        }
        return this;
    }

    public void remove(Characteristic<?> c) {
        characteristics.remove(c);
    }

    public String getName() { return this.name; }

    public ArrayList<Characteristic<?>> getCharacteristics() {
        return new ArrayList<Characteristic<?>>(this.characteristics);
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
     * String representation of the CharacteristicList
     *
     * @return string representation of the characteristics with non-null values
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name+":[");
        for (Characteristic<?> i : characteristics) {
            if (i.getValue() != null) {
                result.append(i);
            }
        }
        return result + "]";
    }
}
