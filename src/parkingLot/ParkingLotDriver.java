package parkingLot;

import java.util.Scanner;

public class ParkingLotDriver {
    public static void main(String[] args){
        ParkingLotService parkingLotService = new ParkingLotService();
        Scanner scanner = new Scanner(System.in);
        String command;
        while (!(command = scanner.next()).equals("exit")){
            if ("create_parking_lot".equals(command)){
                String parkingLotId = scanner.next();
                int noOfFloors = scanner.nextInt();
                int noOfSlotsPerFloor = scanner.nextInt();
                parkingLotService.addParkingLot(parkingLotId, noOfFloors, noOfSlotsPerFloor);
            }
            if ("park_vehicle".equals(command)){
                String type = scanner.next();
                String registrationNo = scanner.next();
                String color = scanner.next();
                parkingLotService.parkVehicle(type,registrationNo,color);
            }
            if ("unpark_vehicle".equals(command)){
                String ticketId = scanner.next();
                parkingLotService.unParkVehicle(ticketId);
            }
            if ("display".equals(command)){
                String displayType = scanner.next();
                String vehicleType = scanner.next();
                parkingLotService.display(displayType,vehicleType);
            }
        }
    }
}
