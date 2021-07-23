public class ParkingSpaces extends Land{
    ParkingSpaces(int ListingNumber, String location, int price, int area,int spots,int height) {

        super(ListingNumber, location, price, area);
        this.add(new Characteristic("No of Rooms",spots));
        this.add(new Characteristic("No of Rooms",height));
    }

}
