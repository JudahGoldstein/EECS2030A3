/**
 *
 */
public class MlsRecord {
    String record;
    Type other = new Type("other");

    /**
     * @param t Type object the toString of which is to be added to the record
     */
    public void addType(Type t) {
        record += t;
    }

    /**
     * @param c characteristc to be added to the record, will be added to an "other" Type appended at the end of the record
     */
    public void addCharacteristic(Characteristic<?> c) {
        other.add(c);
    }

    /**
     * @return MLS record string
     *<br>
     * e.g. [(name:value)(name:value)(name:value)][(name:value)][(name:value)(name:value)]
     */
    public String getRecord() {
        return record + other;
    }
}
