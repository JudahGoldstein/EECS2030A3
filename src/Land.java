import java.util.ArrayList;

public class Land extends CharacteristicsList{
    int ListingNumber;
    ArrayList<Characteristic<?>> characteristics = new ArrayList<>();

    Land(int ListingNumber,String location,int price,int area){
        super(ListingNumber);
        this.add(new Characteristic("Location",location));
        this.add(new Characteristic("Price",price));
        this.add(new Characteristic("area",area));
    }

}
