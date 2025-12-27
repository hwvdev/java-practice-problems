package airline.management.system.repo;

import airline.management.system.model.Aircraft;
import airline.management.system.model.Flight;
import airline.management.system.model.Seat;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class FlightRepo {
    private static final FlightRepo INSTANCE;
    private final AircraftRepo aircraftRepo = AircraftRepo.getAircarftInstance();
    private final Map<String, Flight> flightMap = new ConcurrentHashMap<>();

    static {
        INSTANCE = new FlightRepo();
    }

    public static FlightRepo getFlightRepoInstance() {
        return INSTANCE;
    }

    public synchronized void addFlight(Flight flight) {
        Aircraft aircraft = flight.getAircraft();
        String flightNo = aircraft.getFlightNo();
        aircraftRepo.getAircraft(flightNo).orElseThrow(() -> new RuntimeException("Aircraft is Not Registered"));
        flightMap.put(flight.getFlightNo(), flight);
    }

    public Map<String, Flight> getFlightMap() {
        return flightMap;
    }

    public Flight getFlightInfo(String flightNo) {
        return flightMap.getOrDefault(flightNo, null);
    }

    public void updateFlightStatus(String flightNo, Flight.FlightStatus flightStatus) {
        Flight flight = flightMap.computeIfPresent(flightNo, (k, v) -> {
            v.setStatus(flightStatus);
            return v;
        });
    }

    public Seat reserve(String flightNo) {
        AtomicReference<Seat> reservedSeat = new AtomicReference<>();
        return Objects.requireNonNull(flightMap.computeIfPresent(flightNo, (k, v) -> {
            reservedSeat.set(v.reserveSeat());
            return v;
        })).reserveSeat();
    }

    public void unReserveSeat(String flightNo, String seatNo) {
        flightMap.computeIfPresent(flightNo, (k, v) -> {
            v.unReserveSeat(seatNo);
            return v;
        });
    }

    // in case of more than 1 param is to update then create POJO and update all in 1 go
    // updateFlightInfo(String flightNo, FlightDTO flightDto) flightDto{endTime, Status, startTime, etc}
}
