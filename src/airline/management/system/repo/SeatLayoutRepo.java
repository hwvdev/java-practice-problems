package airline.management.system.repo;

import airline.management.system.data.store.InMemoryStore;
import airline.management.system.model.Seat;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SeatLayoutRepo {
    private final Map<String, List<Seat>> aircraftSeatMap;

    public SeatLayoutRepo(InMemoryStore store) {
        this.aircraftSeatMap = store.aircraftSeatMap;
    }

    public void save(String aircraftNo, List<Seat> seatList) {
        aircraftSeatMap.put(aircraftNo, List.copyOf(seatList));
    }

    public Optional<List<Seat>> findByAircraftId(String aircraftId) {
        return Optional.ofNullable(aircraftSeatMap.get(aircraftId)).map(List::copyOf);
    }

}
