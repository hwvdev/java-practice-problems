package parkingLotV2.parkingStrategy;

import parkingLotV2.ParkingLot;
import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.enums.VehicleType;

import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpotDto> getParkingSpot(ParkingLot parkingLot, VehicleType vehicleType);

}
