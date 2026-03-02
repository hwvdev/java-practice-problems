package parkingLotV2.entities;

import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.enums.SpotType;
import parkingLotV2.enums.VehicleType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Floor {
    private final int floorNumber;
    private final Map<Integer, ParkingSpot> parkingSpots;

    public Floor(int floorNumber, Map<Integer, ParkingSpot> parkingSpots) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ConcurrentHashMap<>();
        if (parkingSpots != null && !parkingSpots.isEmpty()) {
            for (Map.Entry<Integer, ParkingSpot> spotEntry: parkingSpots.entrySet()) {
                ParkingSpot parkingSpot = spotEntry.getValue();
                this.parkingSpots.put(spotEntry.getKey(), parkingSpot);
            }
        }
    }

    public Map<Integer, ParkingSpot> getParkingSpots() {
        return Collections.unmodifiableMap(parkingSpots);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void addParkingSpot(ParkingSpot spot) {
        ParkingSpot parkingSpot = new ParkingSpot(spot.getSpotId(), spot.getSpotType());
        parkingSpots.put(spot.getSpotId(), parkingSpot);
    }

    public synchronized Optional<ParkingSpot> getAvailableSpots(VehicleType vehicleType) {
        return parkingSpots.values().stream()
                .filter(spot -> spot.getParkedVehicle().isEmpty() && spot.canFit(vehicleType))
                .min(Comparator.comparing(ParkingSpot::getSpotId));
    }

    public void park(Vehicle vehicle, int spotId) {
        parkingSpots.computeIfPresent(spotId, (k, spot) -> {
            if (spot.getParkedVehicle().isEmpty() && spot.canFit(vehicle.getVehicleType())) {
                spot.park(vehicle);
                return spot;
            } else {
                throw new IllegalStateException("Cannot park at this spot");
            }
        });
    }

    public void unpark(int spotId) {
        parkingSpots.computeIfPresent(spotId, (k, spot) -> {
            if (spot.getParkedVehicle().isEmpty()) {
                throw new IllegalStateException("Already unparked");
            }
            spot.unpark();
            return spot;
        });
    }
}
