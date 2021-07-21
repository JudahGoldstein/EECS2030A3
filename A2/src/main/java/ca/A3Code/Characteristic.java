/**
 * The
 *
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

    /**
     * @param value value to set the characteristic to
     * @return characteristic for fluid interfacing
     */
    public Characteristic<E> setValue(E value) {
        this.value = value;
        return this;
    }

    /**
     * @param name name to set the characteristic to
     * @return characteristic for fluid interfacing
     */
    public Characteristic<E> setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param min minimum value the characteristic be to be valid
     * @return characteristic for fluid interfacing
     */
    public Characteristic<E> setMin(int min) {
        this.min = min;
        return this;
    }

    /**
     * @param max maximum value the characteristic be to be valid
     * @return characteristic for fluid interfacing
     */
    public Characteristic<E> setMax(int max) {
        this.max = max;
        return this;
    }

    public String getName() {
        return name;
    }

    /**
     * validates the value of the charcteristic is between it's minimum and maximum values
     *
     * @return whether or not the characteristic is valid.
     */
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

    /**
     * @return string repersentation of (name:value)
     */
    @Override
    public String toString() {
        return "(" + name + ":" + value + ")";
    }

    /**
     * @param O object to be compared
     * @return weather or not the characteristics share a name (given they are both characteristics)
     */
    @Override
    public boolean equals(Object O) {
        if (O instanceof Characteristic) {
            return ((Characteristic<?>) O).name.equals(name);
        }
        return false;
    }

}