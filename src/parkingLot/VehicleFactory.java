package parkingLot;

import java.util.Locale;

public class VehicleFactory {
    public static Vehicle getVehicle(String type, String registrationNo, String color){
        if (Constants.CAR.equals(type))
            return new Car(registrationNo,color);
        if (Constants.BIKE.equals(type))
            return new Bike(registrationNo,color);
        return new Truck(registrationNo, color);
    }
}
