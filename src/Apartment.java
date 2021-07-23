public class Apartment extends House{
    Apartment(int ListingNumber, String location, int price, int area, int nOfRooms,int FloorNo) {
        super(ListingNumber, location, price, area, nOfRooms);
        this.add(new Characteristic("Floor No",FloorNo));
    }
}
