import java.util.ArrayList;

public class House extends Land {
    int ListingNumber;
    ArrayList<Characteristic<?>> characteristics = new ArrayList<>();

    House(int ListingNumber, String location, int price, int area, int nOfRooms) {
        super(ListingNumber, location, price, area);
        this.add(new Characteristic("No of Rooms",nOfRooms));
    }
}
