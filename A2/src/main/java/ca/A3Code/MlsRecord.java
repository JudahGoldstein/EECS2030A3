public class MlsRecord {
    String record;
    Type other = new Type("other");

    public void addType(Type t) {
        record += t;
    }

    public void addCharacteristic(Characteristic<?> c) {
        other.add(c);
    }

    public String getRecord(){
        return record + other;
    }
}
