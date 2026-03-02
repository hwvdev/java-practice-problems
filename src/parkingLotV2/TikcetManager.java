package parkingLotV2;

import parkingLotV2.dto.ParkingSpotDto;
import parkingLotV2.entities.Ticket;
import parkingLotV2.entities.vehicle.Vehicle;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TikcetManager {
    private final Map<String, Ticket> activeTickets;

    public TikcetManager() {
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpotDto parkingSpot) {
        Ticket ticket = new Ticket(parkingSpot, vehicle);
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public void setExitTime(String ticketId) {
        activeTickets.computeIfPresent(ticketId, (k, v) -> {
            v.setExitTime();
            return v;
        });
    }

    public void removeTicket(String ticketId) {
        activeTickets.remove(ticketId);
    }

    public Map<String, Ticket> getActiveTickets() {
        return Collections.unmodifiableMap(activeTickets);
    }
}
