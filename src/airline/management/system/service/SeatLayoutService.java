package airline.management.system.service;

import airline.management.system.model.Seat;
import airline.management.system.repo.SeatLayoutRepo;

import java.util.List;

public class SeatLayoutService {
    private final SeatLayoutRepo seatLayoutRepo;

    public SeatLayoutService(SeatLayoutRepo seatLayoutRepo) {
        this.seatLayoutRepo = seatLayoutRepo;
    }

    public void saveLayout(String aircraftId, List<Seat> seatList) {
        seatLayoutRepo.save(aircraftId, seatList);
    }

}
