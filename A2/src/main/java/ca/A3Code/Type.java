import java.util.ArrayList;

public class Type {
    ArrayList<Characteristic<?>> characteristics = new ArrayList<>();
    String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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

    public Characteristic<?> getByName(String name) {
        for (Characteristic<?> i : characteristics) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    void addType(Type t) {
        characteristics.addAll(t.characteristics);
    }

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
