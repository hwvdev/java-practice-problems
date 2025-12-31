package airline.management.system.service;

import airline.management.system.model.Aircraft;
import airline.management.system.repo.AircraftRepo;

public class AircraftService {
    private final AircraftRepo aircraftRepo;

    public AircraftService(AircraftRepo aircraftRepo) {
        this.aircraftRepo = aircraftRepo;
    }

    public void registerAircraft(Aircraft aircraft){
        aircraftRepo.save(new Aircraft(aircraft.getAircraftName(), aircraft.getAircraftId()));
    }

}
