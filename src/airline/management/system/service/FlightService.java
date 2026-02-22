package airline.management.system.service;

import airline.management.system.model.Flight;
import airline.management.system.model.FlightStatus;
import airline.management.system.model.Seat;
import airline.management.system.repo.FlightRepo;
import airline.management.system.repo.SeatLayoutRepo;
import airline.management.system.repo.SeatRepo;

import java.util.List;
import java.util.Map;

public class FlightService {
    private final FlightRepo flightRepo;
    private final SeatRepo seatRepo;
    private final SeatLayoutRepo seatLayoutRepo;

    public FlightService(FlightRepo flightRepo, SeatRepo seatRepo, SeatLayoutRepo seatLayoutRepo) {
        this.flightRepo = flightRepo;
        this.seatRepo = seatRepo;
        this.seatLayoutRepo = seatLayoutRepo;
    }

    public void saveFlight(Flight flight) {
        List<Seat> seats = seatLayoutRepo.findByAircraftId(flight.getAircraftId())
                .orElseThrow(() -> new RuntimeException("Seats are not mapped"));

        Object lock = LockRegistry.getLockRegistryInstance().getFlightServiceLock(flight.getFlightId());
        synchronized (lock) {
            seatRepo.loadSeatMap(flight.getFlightId(), seats);
            Flight flightCopy = new Flight(flight.getFlightId(), flight.getAircraftId(), flight.getSource(),
                    flight.getStartTime(), flight.getEndTime(), flight.getDest(), FlightStatus.BOOKING_OPEN);
            flightRepo.saveFlight(flightCopy);
        }
    }

    public void updateFlight(Flight flight) {

        Flight flightCopy = new Flight(flight.getFlightId(), flight.getAircraftId(), flight.getSource(),
                flight.getStartTime(), flight.getEndTime(), flight.getDest(), flight.getFlightStatus());
        Object lock = LockRegistry.getLockRegistryInstance().getFlightServiceLock(flight.getFlightId());
        synchronized (lock) {
            flightRepo.updateFlight(flightCopy);
        }
    }

    public Map<String, Seat> getSeatLayoutMap(String flightId) {
        return seatRepo.seatListByFlightId(flightId);
    }

}
