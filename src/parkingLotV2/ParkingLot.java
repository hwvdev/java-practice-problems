package parkingLotV2;

import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.entities.Floor;
import parkingLotV2.entities.ParkingSpot;
import parkingLotV2.entities.Ticket;
import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.parkingStrategy.ParkingStrategy;
import parkingLotV2.payment.PaymentMethod;
import parkingLotV2.payment.PaymentResponse;
import parkingLotV2.payment.PaymentService;
import parkingLotV2.payment.PaymentStatus;
import parkingLotV2.payment.processor.PaymentProcessor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ParkingLot {
    private final Map<Integer, Floor> floors;
    private final int floorsCount;
    private final TicketManager ticketManager;
    private final ParkingStrategy parkingStrategy;
    private final PaymentService paymentService;

    public ParkingLot(Map<Integer, Floor> floors, ParkingStrategy parkingStrategy, PaymentService paymentService) {
        this.floors = floors;
        this.floorsCount = floors.size();
        this.ticketManager = new TicketManager();
        this.parkingStrategy = parkingStrategy;
        this.paymentService = paymentService;
    }

    private void addFloor(Floor floor) {
        floors.put(floor.floorNumber(), new Floor(floor.floorNumber(), floor.parkingSpots()));
    }

    public Map<String, Ticket> getTicketmap() {
        return ticketManager.getActiveTicket();
    }

    public Map<Integer, Floor> getFloors() {
        return floors;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public synchronized Optional<Ticket> parkVehicle(Vehicle vehicle) {
        Optional<Ticket> ticketOptional = Optional.empty();
        Optional<ParkingSpotDto> spotDto = parkingStrategy.getParkingSpot(this, vehicle.getVehicleType());
        if (spotDto.isPresent()) {
            floors.get(spotDto.get().floorNumber()).park(vehicle, spotDto.get().spotId());
            return Optional.ofNullable(ticketManager.generateTicket(vehicle, spotDto.get()));
        }
        return ticketOptional;
    }

    public synchronized Ticket unparkVehicle(String ticketId) throws InterruptedException {
        AtomicReference<Ticket> ticketRef = ticketManager.closeTicket(ticketId);
        Ticket ticket = ticketRef.get();
        Optional<PaymentResponse> paymentResponse = paymentService.processPayment(ticket, PaymentMethod.UPI);

        if (paymentResponse.isPresent() && PaymentStatus.SUCCESS.equals(paymentResponse.get().getPaymentStatus())) {
            ParkingSpotDto parkingSpotDto = ticket.getParkingSpotDto();
            ParkingSpot parkingSpot = Optional.ofNullable(floors.get(parkingSpotDto.floorNumber()))
                    .map(Floor::parkingSpots)
                    .map(spot -> spot.get(parkingSpotDto.spotId()))
                    .orElseThrow(() -> new IllegalStateException("Invalid"));
            ticket.setParkingFee(paymentResponse.get().getAmount());
            parkingSpot.unpark();
        }
        System.out.println(ticket);
        return ticket;
    }
}
