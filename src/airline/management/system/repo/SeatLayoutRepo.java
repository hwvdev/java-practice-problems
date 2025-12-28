package airline.management.system.repo;

import airline.management.system.model.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SeatLayoutRepo {
    private final Map<String, List<Seat>> seatMapList = new ConcurrentHashMap<>();
    public SeatLayoutRepo(Map<String, List<Seat>> seatMap) {
        this.seatMapList.putAll(seatMap);
    }

    public List<Seat> getSeatLayoutById(String aircraftId) {
        return new ArrayList<>(seatMapList.get(aircraftId));
    }

    public void saveSeatLayout(String aircraftNo, List<Seat> seatLayout) {
        seatMapList.putIfAbsent(aircraftNo, new ArrayList<>(seatLayout));
    }

}
