package parkingLotV2.entities;

import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.enums.SpotType;
import parkingLotV2.enums.VehicleType;

import java.util.Optional;

public class ParkingSpot {
    private final int spotId;
    private final SpotType spotType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotId, SpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.parkedVehicle = null;
    }

    public synchronized void park(Vehicle vehicle) {
        if (parkedVehicle == null) {
            this.parkedVehicle = vehicle;
        } else {
            throw new IllegalStateException("Parking spot is already occupied.");
        }
    }

    public synchronized void unpark() {
        if (parkedVehicle == null) {
            throw new IllegalStateException("Parking spot is already empty.");
        }
        this.parkedVehicle = null;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Optional<Vehicle> getParkedVehicle() {
        return Optional.ofNullable(parkedVehicle);
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotId=" + spotId +
                ", spotType=" + spotType +
                ", parkedVehicle=" + parkedVehicle +
                '}';
    }

    public boolean canFit(VehicleType vehicleType) {
        switch (spotType) {
            case BIKE:
                return vehicleType == VehicleType.BIKE;
            case CAR:
                return vehicleType == VehicleType.CAR;
            case TRUCK:
                return vehicleType == VehicleType.TRUCK;
            default:
                return false;
        }
    }

}
