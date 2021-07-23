package A3;

/**
 * The
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

    public String getName() {
        return name;
    }

    public E getValue() {
        return value;
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