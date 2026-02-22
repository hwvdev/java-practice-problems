package airline.management.system.repo;

import airline.management.system.data.store.InMemoryStore;
import airline.management.system.model.Seat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class SeatRepo {
    private final Map<String, ConcurrentMap<String, Seat>> seatListByFlightId;

    public SeatRepo(InMemoryStore store) {
        this.seatListByFlightId = store.currentSeatMapByFlightId;
    }

    public void loadSeatMap(String flightId, List<Seat> seatList) {
        seatListByFlightId.compute(flightId, (id, existingSeats) -> {
            if (existingSeats != null)
                throw new RuntimeException("Seats are already loaded");
            return seatList.stream()
                    .collect(Collectors.toMap(
                            Seat::getSeatNo,
                            seat -> new Seat(seat.getSeatNo(), seat.getSeatType(), seat.getPrice(), false),
                            (v1, v2) -> v1, ConcurrentHashMap::new));
        });
    }

    public Map<String, Seat> seatListByFlightId(String flightId) {
        return Map.copyOf(seatListByFlightId.get(flightId));
    }

    public void reserve(String flightId, String seatNo) {
        seatListByFlightId.compute(flightId, (id, existingSeatMap) -> {
            if (existingSeatMap == null) {
                throw new RuntimeException("Seat is not available");
            }
            existingSeatMap.compute(seatNo, (seatId, seat) -> {
                if (seat==null)
                    throw new RuntimeException("invalid seat");
                if (seat.isReserved())
                    throw new RuntimeException("Seat is already reserved");
                return seat.reserve();
            });
            return existingSeatMap;
        });
    }

    public void unReserve(String flightNo, String seatNo) {
        seatListByFlightId.compute(flightNo, (id, existingSeats) -> {
            if(existingSeats==null)
                throw new RuntimeException("seats are not mapped");
            existingSeats.compute(seatNo, (seatId, seat) -> {
                if (seat==null)
                    throw new RuntimeException("invalid seat");
                if (!seat.isReserved())
                    throw new RuntimeException("Seat is not reserved");
                return seat.unReserve();
            });
            return existingSeats;
        });
    }

    public void rollbackToUnreserve(String flightId, String seatId) {
        seatListByFlightId.computeIfPresent(flightId, (fid, existSeatMap) -> {
            existSeatMap.compute(seatId, (id, seat) -> {
                if (seat == null)
                    throw new RuntimeException("invalid Seat");
                if (!seat.isReserved())
                    return seat;
                return seat.unReserve();
            });
            return existSeatMap;
        });
    }


    public void rollbackToReserve(String flightId, String seatId) {
        seatListByFlightId.computeIfPresent(flightId, (fid, existSeatMap) -> {
            existSeatMap.compute(seatId, (id, seat) -> {
                if (seat == null)
                    throw new RuntimeException("invalid Seat");
                if (seat.isReserved())
                    return seat;
                return seat.reserve();
            });
            return existSeatMap;
        });
    }
}
