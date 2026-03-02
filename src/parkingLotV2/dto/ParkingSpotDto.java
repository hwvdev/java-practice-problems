package parkingLotV2.dto;

import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.enums.SpotType;

public class ParkingSpotDto {
    private final int spotId;
    private final SpotType spotType;
    private final int floorNumber;

    public ParkingSpotDto(int spotId, SpotType spotType, int floorNumber) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.floorNumber = floorNumber;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public String toString() {
        return "ParkingSpotDto{" +
                "spotId=" + spotId +
                ", spotType=" + spotType +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
