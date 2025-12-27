package airline.management.system.repo;

import airline.management.system.model.Aircraft;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AircraftRepo {
    private final Map<String, Aircraft> registeredAircraft = new ConcurrentHashMap<>();
    private static final AircraftRepo AIRCARFT_INSTANCE;
    static {
        AIRCARFT_INSTANCE = new AircraftRepo();
    }
    private AircraftRepo() {}
    public static AircraftRepo getAircarftInstance() {
        return AIRCARFT_INSTANCE;
    }
    public void addAircraft(Aircraft aircraft) {
        registeredAircraft.put(aircraft.getFlightNo(), aircraft);
    }
    public Optional<Aircraft> getAircraft(String flightNo) {
        return Optional.ofNullable(registeredAircraft.getOrDefault(flightNo, null));
    }

}
