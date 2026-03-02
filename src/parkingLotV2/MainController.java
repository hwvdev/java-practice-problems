package parkingLotV2;

import parkingLotV2.entities.Floor;
import parkingLotV2.entities.ParkingSpot;
import parkingLotV2.entities.Ticket;
import parkingLotV2.entities.vehicle.Bike;
import parkingLotV2.entities.vehicle.Car;
import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.enums.SpotType;
import parkingLotV2.enums.VehicleType;
import parkingLotV2.parkingStrategy.impl.NearestSpot;
import parkingLotV2.paymentStrategy.impl.FlatRate;

import java.math.BigDecimal;
import java.util.Map;

public class MainController {
    public static void main(String[] args) {

        // Create a parking lot with 2 floors, each having 2 bike spots, 3 car spots, and 1 truck spot
        ParkingSpot bikeSpot1 = new ParkingSpot(1, SpotType.BIKE);
        ParkingSpot bikeSpot2 = new ParkingSpot(2, SpotType.BIKE);
        ParkingSpot bikeSpot3 = new ParkingSpot(3, SpotType.BIKE);
        ParkingSpot bikeSpot4 = new ParkingSpot(4, SpotType.BIKE);

        ParkingSpot carSpot1 = new ParkingSpot(5, SpotType.CAR);
        ParkingSpot carSpot2 = new ParkingSpot(6, SpotType.CAR);
        ParkingSpot carSpot3 = new ParkingSpot(7, SpotType.CAR);

        ParkingSpot truckSpot1 = new ParkingSpot(8, SpotType.TRUCK);
        ParkingSpot truckSpot2 = new ParkingSpot(9, SpotType.TRUCK);

        Map<Integer, ParkingSpot> floor1Spots = Map.of(
                bikeSpot1.getSpotId(), bikeSpot1,
                bikeSpot2.getSpotId(), bikeSpot2,
                carSpot1.getSpotId(), carSpot1,
                carSpot2.getSpotId(), carSpot2,
                truckSpot1.getSpotId(), truckSpot1
        );
        Map<Integer, ParkingSpot> floor2Spots = Map.of(
                bikeSpot3.getSpotId(), bikeSpot3,
                bikeSpot4.getSpotId(), bikeSpot4,
                carSpot3.getSpotId(), carSpot3,
                truckSpot2.getSpotId(), truckSpot2
        );

        Floor floor = new Floor(1, floor1Spots);
        Floor floor2 = new Floor(2, floor2Spots);
        Map<Integer, Floor> floors = Map.of(
                floor.floorNumber(), floor,
                floor2.floorNumber(), floor2
        );

        ParkingLot parkingLot = new ParkingLot(floors, new NearestSpot(), new FlatRate(new BigDecimal(10.0), "Rs."));

        // Create some vehicles
        Vehicle bike1 = new Bike("BIKE123", VehicleType.BIKE);
        Vehicle bike2 = new Bike("BIKE456", VehicleType.BIKE);
        Vehicle bike3 = new Bike("BIKE789", VehicleType.BIKE);


        Vehicle car1 = new Car("CAR789", VehicleType.CAR);
        Vehicle car2 = new Car("CAR789", VehicleType.CAR);
        // Park the vehicles

        try {
            Ticket ticket1 = parkingLot.parkVehicle(bike1).get();
            Thread.sleep(100);
            Ticket ticket2 = parkingLot.parkVehicle(bike2).get();
            Thread.sleep(900);
            Ticket ticket3 = parkingLot.parkVehicle(bike3).get();
            Ticket ticket4 = parkingLot.parkVehicle(car1).get();

            System.out.println("Parking lot state after parking vehicles:");
            parkingLot.getFloors().forEach((floorNumber, floorObj) -> {
                System.out.println("Floor " + floorNumber + ":");
                floorObj.parkingSpots().forEach((spotId, spot) -> {
                    System.out.println("  Spot " + spotId + " (" + spot.getSpotType() + "): " +
                            (!spot.getParkedVehicle().isEmpty() ? "Occupied by " + spot.getParkedVehicle() : "Available"));
                });
            });

            for (Map.Entry<String, Ticket> m : parkingLot.getTicketmap().entrySet())
                System.out.println(m.getValue());

            Thread.sleep(1000);
            parkingLot.unparkVehicle(ticket1.getTicketId());
            parkingLot.unparkVehicle(ticket4.getTicketId());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Parking lot state after parking vehicles:");
        parkingLot.getFloors().forEach((floorNumber, floorObj) -> {
            System.out.println("Floor " + floorNumber + ":");
            floorObj.parkingSpots().forEach((spotId, spot) -> {
                System.out.println("  Spot " + spotId + " (" + spot.getSpotType() + "): " +
                        (!spot.getParkedVehicle().isEmpty() ? "Occupied by " + spot.getParkedVehicle() : "Available"));
            });
        });

        for (Map.Entry<String, Ticket> m : parkingLot.getTicketmap().entrySet())
            System.out.println(m.getValue());

    }
}
