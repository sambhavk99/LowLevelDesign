package parkingLot;

public class Truck extends Vehicle{
    public Truck(String registrationNo, String color){
        this.setType(Constants.TRUCK);
        this.setColor(color);
        this.setRegistrationNo(registrationNo);
    }
}
