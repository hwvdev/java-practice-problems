package parkingLotV2.entities;

import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.enums.SpotType;

import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final ParkingSpotDto parkingSpotDto;
    private final long entryTime;
    private final Vehicle vehicle;
    private long exitTime = 0L;
    private double parkingFee;

    public Ticket(ParkingSpotDto parkingSpot, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.parkingSpotDto = parkingSpot;
        this.entryTime = System.currentTimeMillis();
        this.vehicle = vehicle;
        this.parkingFee = 0;
    }

    public SpotType spotType() {
        return parkingSpotDto.spotType();
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setExitTime() {
        if (exitTime == 0L) {
            this.exitTime = System.currentTimeMillis();
        } else {
            throw new IllegalStateException("Exit time is already set.");
        }
    }

    public void setParkingFee(Double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public ParkingSpotDto getParkingSpotDto() {
        return parkingSpotDto;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", parkingSpotDto=" + parkingSpotDto +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", vehicle=" + vehicle +
                ", parkingFee=" + parkingFee +
                '}';
    }
}
