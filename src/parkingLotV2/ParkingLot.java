package parkingLotV2;

import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.entities.Floor;
import parkingLotV2.entities.ParkingSpot;
import parkingLotV2.entities.Ticket;
import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.parkingStrategy.ParkingStrategy;
import parkingLotV2.paymentStrategy.PaymentStrategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingLot {
    private final Map<Integer, Floor> floors;
    private final int floorsCount;
    private final TikcetManager ticketManager;
    private final ParkingStrategy parkingStrategy;
    private final PaymentStrategy paymentStrategy;

    private void addFloor(Floor floor) {
        floors.put(floor.getFloorNumber(), new Floor(floor.getFloorNumber(), floor.getParkingSpots()));
    }

    public ParkingLot(Map<Integer, Floor> floors, ParkingStrategy parkingStrategy, PaymentStrategy paymentStrategy) {
        this.floors = floors;
        this.floorsCount = floors.size();
        this.ticketManager = new TikcetManager();
        this.parkingStrategy = parkingStrategy;
        this.paymentStrategy = paymentStrategy;
    }

    public Map<String, Ticket> getTicketmap() {
        return ticketManager.getActiveTickets();
    }

    public Map<Integer, Floor> getFloors() {
        return floors;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpotDto> spotDto = parkingStrategy.getParkingSpot(this, vehicle.getVehicleType());
        if (spotDto.isPresent()) {
            floors.get(spotDto.get().getFloorNumber()).park(vehicle, spotDto.get().getSpotId());
            return ticketManager.generateTicket(vehicle, spotDto.get());
        }
        return null;
    }

    public synchronized Ticket unparkVehicle(Ticket ticket) {
        ticketManager.setExitTime(ticket.getTicketId());
        ParkingSpotDto parkingSpotDto = ticket.getParkingSpotDto();
        ParkingSpot parkingSpot = floors.get(parkingSpotDto.getFloorNumber()).getParkingSpots().get(parkingSpotDto.getSpotId());
        parkingSpot.unpark();
        Double amount = paymentStrategy.pay(ticket);
        ticket.setParkingFee(amount);
        ticketManager.removeTicket(ticket.getTicketId());
        System.out.println(ticket);
        return ticket;
    }
}
