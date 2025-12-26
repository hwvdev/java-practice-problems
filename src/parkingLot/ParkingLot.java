package parkingLot;

import parkingLot.DTO.FloorSlot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    String parking_lot_id;
    int no_of_floors;
    int no_of_slots_per_floor;
    List<FloorSlot> floorSlotList;

    public ParkingLot(String lot_id, int no_of_floors, int no_of_slots_per_floor) {
        this.parking_lot_id = lot_id;
        this.no_of_floors = no_of_floors;
        this.no_of_slots_per_floor = no_of_slots_per_floor;
        this.floorSlotList = new ArrayList<>(no_of_floors);
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot("PR1234", 2, 6);
        // display free slots


    }
}
