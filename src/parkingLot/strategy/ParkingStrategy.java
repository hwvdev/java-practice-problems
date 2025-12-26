package parkingLot.strategy;

import parkingLot.DTO.Slot;
import parkingLot.DTO.Vehicle;

import java.util.List;

public interface ParkingStrategy {

    public Slot findSlot(Vehicle vehicle, List<Slot> availableSlots);

}
