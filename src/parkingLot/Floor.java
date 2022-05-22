package parkingLot;

import java.util.*;

public class Floor {
    private int floorNo;
    private HashMap<Integer,Slot> slots;
    private HashMap<String,TreeSet<Integer>> availableSlots;
    private HashMap<String,Set<Integer>> bookedSlots;

    public Floor(int floorNo){
        this.floorNo = floorNo;
        this.setBookedSlots();
        this.setAvailableSlots();
        slots = new HashMap<>();
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public HashMap<String, TreeSet<Integer>> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(HashMap<String, TreeSet<Integer>> availableSlots) {
        this.availableSlots = availableSlots;
    }

    private void setAvailableSlots() {
        this.availableSlots = new HashMap<>();
        availableSlots.put(Constants.BIKE,new TreeSet<>());
        availableSlots.put(Constants.CAR,new TreeSet<>());
        availableSlots.put(Constants.TRUCK,new TreeSet<>());
    }

    private void setBookedSlots() {
        this.bookedSlots = new HashMap<>();
        bookedSlots.put(Constants.BIKE,new HashSet<>());
        bookedSlots.put(Constants.CAR,new HashSet<>());
        bookedSlots.put(Constants.TRUCK,new HashSet<>());
    }

    public HashMap<Integer, Slot> getSlots() {
        return slots;
    }

    public void setSlots(HashMap<Integer, Slot> slots) {
        this.slots = slots;
    }

    public HashMap<String, Set<Integer>> getBookedSlots() {
        return bookedSlots;
    }

    public void setBookedSlots(HashMap<String, Set<Integer>> bookedSlots) {
        this.bookedSlots = bookedSlots;
    }
}
