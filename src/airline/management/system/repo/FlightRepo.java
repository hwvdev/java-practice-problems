package airline.management.system.repo;

import airline.management.system.data.store.InMemoryStore;
import airline.management.system.model.Flight;
import airline.management.system.model.FlightStatus;

import java.util.Map;
import java.util.Optional;

public final class FlightRepo {
    private final Map<String, Flight> flightIdFlightMap;

    public FlightRepo(InMemoryStore store) {
        this.flightIdFlightMap = store.flightIdFlightMap;
    }

    public void saveFlight(Flight flight) {
        flightIdFlightMap.compute(flight.getFlightId(), (id, exstingFlight) -> {
            if (exstingFlight!=null)
                throw new RuntimeException("Flight already registered");
            return flight;
        });
    }

    public Flight getFlightById(String flightId) {
        return Optional.ofNullable(flightIdFlightMap.get(flightId)).map(
                flight -> new Flight(flight.getFlightId(), flight.getAircraftId(), flight.getSource(), flight.getStartTime(), flight.getEndTime(), flight.getDest(), flight.getFlightStatus()))
                .orElseThrow(() -> new RuntimeException("Invalid Flight"));
    }

    public void updateFlight(Flight updateFlight) {
        flightIdFlightMap.compute(updateFlight.getFlightId(), (id, exisitngFlight) -> {
            if (exisitngFlight==null)
                throw new RuntimeException("Flight does not exist");
            if (exisitngFlight.getFlightStatus()== FlightStatus.CANCELLED)
                throw new RuntimeException("Cannot update cancelled flight");
            return updateFlight;
        });
    }

}
