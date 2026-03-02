package parkingLotV2.dto;

import parkingLotV2.enums.SpotType;

public record ParkingSpotDto(int spotId, SpotType spotType, int floorNumber) {

    @Override
    public String toString() {
        return "ParkingSpotDto{" +
                "spotId=" + spotId +
                ", spotType=" + spotType +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
