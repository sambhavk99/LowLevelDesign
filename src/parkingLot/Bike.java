package parkingLot;

public class Bike extends Vehicle {
    public Bike(String registrationNo, String color){
        this.setType(Constants.BIKE);
        this.setColor(color);
        this.setRegistrationNo(registrationNo);
    }
}
