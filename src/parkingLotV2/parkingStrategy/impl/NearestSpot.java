package parkingLotV2.parkingStrategy.impl;

import parkingLotV2.ParkingLot;
import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.entities.Floor;
import parkingLotV2.entities.ParkingSpot;
import parkingLotV2.enums.VehicleType;
import parkingLotV2.parkingStrategy.ParkingStrategy;

import java.util.Map;
import java.util.Optional;

public class NearestSpot implements ParkingStrategy {
    @Override
    public Optional<ParkingSpotDto> getParkingSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        Optional<ParkingSpotDto> parkingSpotDto = Optional.empty();
        for (Map.Entry<Integer, Floor> floorEntry : parkingLot.getFloors().entrySet()) {
            Floor floor = floorEntry.getValue();
            Optional<ParkingSpot> availableSpot = floor.getAvailableSpots(vehicleType);
            if (availableSpot.isPresent())
                return Optional.of(new ParkingSpotDto(availableSpot.get().getSpotId(), availableSpot.get().getSpotType(), floorEntry.getKey()));
        }
        return parkingSpotDto;
    }

}
