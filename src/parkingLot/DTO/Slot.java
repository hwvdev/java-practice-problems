package parkingLot.DTO;

import parkingLot.enums.VehicleType;

public class Slot {
    Vehicle parkedVehicle;
    int floorNo;
    int slotNo;
    VehicleType vehicleType;
    boolean isAvailable;

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Slot(int floorNo, int slotNo, VehicleType vehicleType) {
        this.parkedVehicle = null;
        this.floorNo = floorNo;
        this.slotNo = slotNo;
        this.vehicleType = vehicleType;
        this.isAvailable = true;
    }

    public boolean canPark(Vehicle vehicle) {
        if (isAvailable && canFit(vehicle)) return true;
        return false;
    }

    private boolean canFit(Vehicle vehicle) {
        if (VehicleType.BIKE == vehicle.type) return true;
        if (VehicleType.CAR == vehicle.type) return true;
        if (VehicleType.TRUCK == vehicle.type) return true;
        return false;
    }

    public Vehicle unpark() {
        Vehicle parkedVehicle = getParkedVehicle();
        isAvailable = true;
        parkedVehicle = null;
        return parkedVehicle;
    }

}
