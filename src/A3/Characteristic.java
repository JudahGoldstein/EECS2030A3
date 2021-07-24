package A3;

/**
 * A wrapper class for each attribute of the MLS record
 *
 * @param <E> the data type of the characteristic
 */
class Characteristic<E> {
    String name;
    E value;

    /**
     * @param name required name of A3.Characteristic for constructor
     */
    Characteristic(String name,E value){
        this.name = name;
        this.value=value;
    }

    /**
     * Get the name associated with the Characteristic
     * @return the name associated with the Characteristic
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of the Characteristic
     * @return the value of the Characteristic
     */
    public E getValue() {
        return value;
    }

    /**
     * String representation of the Characteristic
     *
     * @return string representation of (name:value)
     */
    @Override
    public String toString() {
        return "(" + name + ":" + value + ")";
    }

    /**
     * Check equality of one Characteristic to another
     *
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