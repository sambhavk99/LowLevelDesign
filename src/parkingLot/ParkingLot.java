package parkingLot;

import java.util.*;

public class ParkingLot {

    private String id;
    private HashMap<Integer,Floor> floors;
    private HashMap<String,TreeSet<Integer>> availableFloors;

    public ParkingLot(String id){
        this.id = id;
        floors = new HashMap<>();
        availableFloors = new HashMap<>();
        availableFloors.put(Constants.BIKE,new TreeSet<>());
        availableFloors.put(Constants.CAR,new TreeSet<>());
        availableFloors.put(Constants.TRUCK,new TreeSet<>());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<Integer, Floor> getFloors() {
        return floors;
    }

    public void setFloors(HashMap<Integer, Floor> floors) {
        this.floors = floors;
    }

    public HashMap<String, TreeSet<Integer>> getAvailableFloors() {
        return availableFloors;
    }

    public void setAvailableFloors(HashMap<String, TreeSet<Integer>> availableFloors) {
        this.availableFloors = availableFloors;
    }

}
