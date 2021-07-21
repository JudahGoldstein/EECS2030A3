/**
 * The 
 * @param <E> the data type of the characteristic
 */
class Characteristic<E> {
    String name;
    E value;
    int min = Integer.MIN_VALUE;
    int max = Integer.MAX_VALUE;

    public E getValue() {
        return value;
    }

    public Characteristic<E> setValue(E value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public Characteristic<E> setName(String name) {
        this.name = name;
        return this;
    }

    public Characteristic<E> setMin(int min) {
        this.min = min;
        return this;
    }

    public Characteristic<E> setMax(int max) {
        this.max = max;
        return this;
    }

    public boolean validate() {
        if (value instanceof String) {
            return ((String) value).length() <= max && ((String) value).length() >= min;
        }
        if (value instanceof Integer) {
            return (int) value <= max && (int) value >= min;
        }
        if (value instanceof Boolean) {
            return true;
        }
        if (value instanceof Double) {
            return (Double) value <= max && (Double) value >= min;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + name + ":" + value + ")";
    }

    @Override
    public boolean equals(Object O) {
        if (O instanceof Characteristic) {
            return ((Characteristic<?>) O).name.equals(name);
        }
        return false;
    }

}