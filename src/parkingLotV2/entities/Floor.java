package parkingLotV2.entities;

import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.enums.VehicleType;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public record Floor(int floorNumber, Map<Integer, ParkingSpot> parkingSpots) {
    public Floor(int floorNumber, Map<Integer, ParkingSpot> parkingSpots) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ConcurrentHashMap<>();
        if (parkingSpots != null && !parkingSpots.isEmpty()) {
            for (Map.Entry<Integer, ParkingSpot> spotEntry : parkingSpots.entrySet()) {
                ParkingSpot parkingSpot = spotEntry.getValue();
                this.parkingSpots.put(spotEntry.getKey(), parkingSpot);
            }
        }
    }

    @Override
    public Map<Integer, ParkingSpot> parkingSpots() {
        return Collections.unmodifiableMap(parkingSpots);
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
