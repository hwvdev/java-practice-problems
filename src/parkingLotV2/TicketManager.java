package parkingLotV2;

import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.entities.Ticket;
import parkingLotV2.entities.vehicle.Vehicle;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class TicketManager {
    private final Map<String, Ticket> activeTickets;

    public TicketManager() {
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public Map<String, Ticket> getActiveTicket() {
        return Collections.unmodifiableMap(activeTickets);
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpotDto parkingSpot) {
        Ticket ticket = new Ticket(parkingSpot, vehicle);
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public Ticket closeTicket(String ticketId) {
        return activeTickets.compute(ticketId, (k, existingticket) -> {
            if (existingticket == null)
                throw new IllegalStateException("Invalid Ticket");
            existingticket.setExitTime();
            return existingticket;
        });
    }
}
