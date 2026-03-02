package parkingLotV2;

import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.entities.Floor;
import parkingLotV2.entities.Money;
import parkingLotV2.entities.Ticket;
import parkingLotV2.entities.vehicle.Vehicle;
import parkingLotV2.parkingStrategy.ParkingStrategy;
import parkingLotV2.payment.PaymentMethod;
import parkingLotV2.payment.PaymentResponse;
import parkingLotV2.payment.PaymentService;
import parkingLotV2.payment.PaymentStatus;

import java.math.BigDecimal;
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
        Ticket ticket = ticketManager.closeTicket(ticketId);
        Money amount = paymentService.calcFee(ticket);
        ticket.setParkingFee(amount);
        int floorNo = ticket.getParkingSpotDto().floorNumber();
        int spotId = ticket.getParkingSpotDto().spotId();
        floors.get(floorNo).unpark(spotId);
        System.out.println(ticket);
        return ticket;
    }

    public PaymentResponse pay(Money amount, String ticketId) {
        AtomicReference<PaymentResponse> responseAtomicReference = new AtomicReference<>();
        ticketManager.getActiveTicket().compute(ticketId, (k, v) -> {
            PaymentResponse paymentResponse = paymentService.pay(amount, PaymentMethod.UPI, v);
            responseAtomicReference.set(paymentResponse);
            return v;
        });
        return responseAtomicReference.get();
    }
}
