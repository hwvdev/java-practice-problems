package parkingLot.strategy;

import parkingLot.DTO.Slot;
import parkingLot.DTO.Vehicle;

import java.util.List;

public class NearestFirstPatkingSpot implements ParkingStrategy {

    @Override
    public Slot findSlot(Vehicle vehicle, List<Slot> availableSlots) {
        return availableSlots.stream().filter(o-> o.isAvailable() && o.canPark(vehicle)).findFirst().orElse(null);
    }

}
