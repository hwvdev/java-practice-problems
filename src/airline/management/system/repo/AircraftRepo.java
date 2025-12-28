package airline.management.system.repo;

import airline.management.system.model.Aircraft;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AircraftRepo {
    private final static AircraftRepo AIRCRAFT_INSTANCE = new AircraftRepo();

    private final Map<String, Aircraft> registeredAircraft = new ConcurrentHashMap<>();

    private AircraftRepo() {}

    public static AircraftRepo getAircraftInstance() {
        return AIRCRAFT_INSTANCE;
    }

    public void addAircraft(Aircraft aircraft) {
        registeredAircraft.put(aircraft.getAircraftId(), aircraft);
    }
    public Optional<Aircraft> getAircraftById(String flightNo) {
        return Optional.ofNullable(registeredAircraft.getOrDefault(flightNo, null));
    }

}
