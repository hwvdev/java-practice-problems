package parkingLot.DTO;

import java.util.ArrayList;
import java.util.List;

public class FloorSlot {
    List<Slot> slotList;
    int floorNo;

    public List<Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<Slot> slotList) {
        this.slotList = slotList;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public void addSlot(Slot slot) {
        slotList.add(slot);
    }

    public Slot getAvailableSlot(Vehicle vehicle) {
        return slotList.stream().filter(o -> o.canPark(vehicle)).findFirst().orElse(null);
    }

}
