package airline.management.system.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public final class SeatInventory {
    private final ConcurrentMap<String, Seat> availableSeats = new ConcurrentHashMap<>();

    public SeatInventory(List<Seat> availableSeats) {
        this.availableSeats.putAll(availableSeats.stream()
                .collect(Collectors.groupingByConcurrent(
                        Seat::getSeatNo,
                        Collectors.collectingAndThen(Collectors.toList(), val -> val.get(0)))));
    }

    public Map<String, Seat> getAvailableSeats() {
        return new HashMap<>(this.availableSeats);
    }

    public Seat getSeat(String seatNo) {
        return availableSeats.getOrDefault(seatNo, null);
    }

    public void reserveSeatBySeatNo(String seatNo, Seat seat) {
        availableSeats.computeIfPresent(seatNo, (k, s) -> {
            if (s.isReserved()) {
                throw new RuntimeException("Seat already reserved");
            }
            return seat;
        });
    }

    public void unreserveSeat(String seatNo, Seat seatToCancel) {
        availableSeats.computeIfPresent(seatNo, (k, s) -> {
            if (!s.isReserved()) {
                throw new RuntimeException("Seat is already unreserved");
            }
            return seatToCancel;
        });
    }
}
