import java.util.ArrayList;

/**
 * A collection of characteristics along with supporting code
 */
public class Type {
    ArrayList<Characteristic<?>> characteristics = new ArrayList<>(); //array of characteristics that make up the Type
    String name;

    /**
     * @param name requiered name of Characteristic for constructor
     */
    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @param c characteristic to be added, will only add it if it has a unique name not in the set already
     * @return Type for fluid interface
     */
    public Type add(Characteristic<?> c) {
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

    /**
     * @return whether or not the Type is valid, i.e. all the characterisitics therein are valid.
     */
    public boolean validate() {
        for (Characteristic<?> i : characteristics) {
            if (!i.validate()) {
                return false;
            }
        }
        return true;
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
     * used to the characteristics of another type into this one. useful for cases such as premaking types that can be applied to multiple types of buildings/structures
     * @param t the Type that all the characteristics will be cloned from
     */
    void addType(Type t) {
        characteristics.addAll(t.characteristics);
    }

    /**
     *
     * @return string repersentation of the characteristics with non-null values
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name + ":[");
        for (Characteristic<?> i : characteristics) {
            if (i.getValue() != null) {
                result.append(i);
            }
        }
        return result + "]";
    }
}
