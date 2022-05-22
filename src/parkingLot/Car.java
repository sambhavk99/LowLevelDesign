package parkingLot;

public class Car extends Vehicle {
    public Car(String registrationNo, String color){
        this.setType(Constants.CAR);
        this.setColor(color);
        this.setRegistrationNo(registrationNo);
    }
}
