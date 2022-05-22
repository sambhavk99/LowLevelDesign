package parkingLot;

import java.util.*;

public class ParkingLotService {

    private HashMap<String,ParkingLot> parkingLots;
    private Set<String> tickets;

    public ParkingLotService() {
        this.parkingLots = new HashMap<>();
        this.tickets = new HashSet<>();
    }

    public void addParkingLot(String parkingLotId, int noOfFloors, int noOfSlotsPerFloor){
        ParkingLot parkingLot = new ParkingLot(parkingLotId);
        parkingLots.put(parkingLotId,parkingLot);
        addFloors(noOfFloors,parkingLot);
        addSlots(noOfSlotsPerFloor, parkingLot);
        System.out.println("Created parking lot with "+ noOfFloors +" floors and "+ noOfSlotsPerFloor +" slots per floor");
    }

    public void addFloors(int noOfFloors, ParkingLot parkingLot){
        for (int floorNo = 1; floorNo <= noOfFloors; floorNo++) {
            Floor floor = new Floor(floorNo);
            parkingLot.getFloors().put(floorNo,floor);
        }
    }

    public void addSlots(int noOfSlotsPerFloor, ParkingLot parkingLot){
        addTruckSlots(noOfSlotsPerFloor,parkingLot);
        addCarSlots(noOfSlotsPerFloor,parkingLot);
        addBikeSlots(noOfSlotsPerFloor,parkingLot);
    }

    private void addTruckSlots(int noOfSlotsPerFloor, ParkingLot parkingLot){
        if (noOfSlotsPerFloor<1)
            return;
        String type = Constants.TRUCK;
        for (Floor floor:parkingLot.getFloors().values()){
            Slot slot = new Slot(type,1, floor.getFloorNo());
            floor.getSlots().put(1,slot);
            floor.getAvailableSlots().get(type).add(1);
            parkingLot.getAvailableFloors().get(type).add(floor.getFloorNo());
        }
    }

    private void addBikeSlots(int noOfSlotsPerFloor, ParkingLot parkingLot){
        if (noOfSlotsPerFloor<2)
            return;
        String type = Constants.BIKE;
        for (Floor floor:parkingLot.getFloors().values()){
            for (int slotNo = 2; slotNo <= Math.min(3,noOfSlotsPerFloor) ; slotNo++) {
                Slot slot = new Slot(type,slotNo, floor.getFloorNo());
                floor.getSlots().put(slotNo,slot);
                floor.getAvailableSlots().get(type).add(slotNo);
            }
            parkingLot.getAvailableFloors().get(type).add(floor.getFloorNo());
        }
    }

    private void addCarSlots(int noOfSlotsPerFloor, ParkingLot parkingLot){
        if (noOfSlotsPerFloor<4)
            return;
        String type = Constants.CAR;
        for (Floor floor:parkingLot.getFloors().values()){
            for (int slotNo = 4; slotNo <= noOfSlotsPerFloor ; slotNo++) {
                Slot slot = new Slot(type, slotNo, floor.getFloorNo());
                floor.getSlots().put(slotNo,slot);
                floor.getAvailableSlots().get(type).add(slotNo);
            }
            parkingLot.getAvailableFloors().get(type).add(floor.getFloorNo());
        }
    }

    public void parkVehicle(String vehicleType, String registrationNo, String color){
        vehicleType = vehicleType.toUpperCase(Locale.ROOT);
        Vehicle vehicle = VehicleFactory.getVehicle(vehicleType,registrationNo,color);
        ParkingLot parkingLot = parkingLots.get(Constants.PARKING_LOT_ID);
        TreeSet<Integer> floors = parkingLot.getAvailableFloors().get(vehicleType);
        if (floors.isEmpty()) {
            System.out.println("Parking Lot Full!");
            return;
        }
        Integer floorNo = floors.first();
        Floor floor = parkingLot.getFloors().get(floorNo);
        TreeSet<Integer> slots = floor.getAvailableSlots().get(vehicleType);
        Integer slotNo = slots.pollFirst();
        Slot slot = floor.getSlots().get(slotNo);
        slot.setVehicle(vehicle);
        floor.getBookedSlots().get(vehicleType).add(slotNo);
        if (slots.isEmpty()) {
            floors.pollFirst();
        }
        String ticketId = parkingLot.getId() + "_" + floorNo + "_" + slot.getSlotNo();
        tickets.add(ticketId);
        System.out.println("Parked Vehicle. Ticked ID : " + ticketId);
    }

    public void unParkVehicle(String ticketId){
        if (!tickets.remove(ticketId)){
            System.out.println("Invalid Ticket");
            return;
        }
        String[] ticketData = ticketId.split("_");
        String parkingLotId =  ticketData[0];
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        Integer floorNo = Integer.parseInt(ticketData[1]);
        Integer slotNo = Integer.parseInt(ticketData[2]);
        Floor floor = parkingLot.getFloors().get(floorNo);
        Slot slot = floor.getSlots().get(slotNo);
        Vehicle vehicle = slot.getVehicle();
        slot.setVehicle(null);
        floor.getAvailableSlots().get(vehicle.getType()).add(slotNo);
        floor.getBookedSlots().get(vehicle.getType()).remove(slotNo);
        parkingLot.getAvailableFloors().get(vehicle.getType()).add(floorNo);
        System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegistrationNo() +
                " and Color: " + vehicle.getColor());
    }

    public void display(String displayType, String vehicleType){
        parkingLots.values().forEach(parkingLot -> {
            if ("free_count".equals(displayType))
                getFreeCount(vehicleType,parkingLot);
            else if ("free_slots".equals(displayType))
                getFreeSlots(vehicleType,parkingLot);
            else if ("occupied_slots".equals(displayType))
                getOccupiedSlots(vehicleType,parkingLot);
            else {
                System.out.println("Invalid Command!");
            }
        });
    }

    private void getFreeCount(String vehicleType, ParkingLot parkingLot){
        vehicleType = vehicleType.toUpperCase(Locale.ROOT);
        for (Floor floor:parkingLot.getFloors().values()) {
            Set<Integer> slots = floor.getAvailableSlots().get(vehicleType);
            System.out.println("No. of free slots for "+ vehicleType +" on Floor "+ floor.getFloorNo() +
                    ": " + slots.size());
        }
    }

    private void getFreeSlots(String vehicleType, ParkingLot parkingLot){
        vehicleType = vehicleType.toUpperCase(Locale.ROOT);
        for (Floor floor:parkingLot.getFloors().values()) {
            Set<Integer> slots = floor.getAvailableSlots().get(vehicleType);
            System.out.println("Free slots for "+ vehicleType +" on Floor "+ floor.getFloorNo() +
                    ": " + slots);
        }
    }

    private void getOccupiedSlots(String vehicleType, ParkingLot parkingLot){
        for (Floor floor:parkingLot.getFloors().values()) {
            Set<Integer> slots = floor.getBookedSlots().get(vehicleType);
            System.out.println("Occupied slots for "+ vehicleType +" on Floor "+ floor.getFloorNo() +
                    ": " + slots);
        }
    }



}
