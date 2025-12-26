package parkingLot.service;

import parkingLot.DTO.FloorSlot;
import parkingLot.DTO.Slot;
import parkingLot.DTO.Ticket;
import parkingLot.DTO.Vehicle;
import parkingLot.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingService {
    List<FloorSlot> floorSlotList;
    public ParkingService(List<FloorSlot> floorSlots) {
        this.floorSlotList = floorSlots;
    }

    public Ticket parkVehicle(Vehicle vehicle, ParkingStrategy parkingStrategy, List<Slot> slotList) {
        Slot slot = parkingStrategy.findSlot(vehicle, slotList);
        if (slot == null) {
            System.out.println("Cannot park. Parking is full");
        }
    return null;
    }

    public List<Slot> getAvailableSlot() {
        List<Slot> availableSlots = new ArrayList<>();
        for (FloorSlot floorSlot: floorSlotList) {
            availableSlots.addAll(floorSlot.getSlotList().stream().filter(Slot::isAvailable).toList());
        }
        return availableSlots;
    }

}
