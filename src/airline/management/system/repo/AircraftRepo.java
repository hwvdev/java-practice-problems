package airline.management.system.repo;

import airline.management.system.data.store.InMemoryStore;
import airline.management.system.exception.AlreadyRegisteredAircraftException;
import airline.management.system.model.Aircraft;

import java.util.Map;
import java.util.Optional;

public class AircraftRepo {
    private final Map<String, Aircraft> aircraftMap;

    private AircraftRepo(InMemoryStore store) {
        this.aircraftMap = store.aircraftMap;
    }

    public void save(Aircraft aircraft) {
        aircraftMap.compute(aircraft.getAircraftId(), (key, craft) -> {
            if (craft!=null)
                throw new AlreadyRegisteredAircraftException("Aircraft is already registered, aircraftId: " + aircraft.getAircraftId());
            return aircraft;
        });
    }

    public Optional<Aircraft> findById(String aircraftId) {
        return Optional.ofNullable(aircraftMap.get(aircraftId));
    }
}
