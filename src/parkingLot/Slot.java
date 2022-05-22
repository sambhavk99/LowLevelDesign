package parkingLot;

public class Slot {
    private Vehicle vehicle;
    private String type;
    private int slotNo;
    private int floorNo;

    public Slot(String type, int slotNo, int floorNo){
        this.type = type;
        this.floorNo = floorNo;
        this.slotNo = slotNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isVacant(){
        return vehicle == null;
    }
}
