package ride.sharing;

import ride.sharing.manager.DriverManagement;
import ride.sharing.repo.DriverRepo;
import ride.sharing.repo.LocationRepo;
import ride.sharing.repo.LockManager;
import ride.sharing.service.DriverService;
import ride.sharing.service.LocationService;
import ride.sharing.service.RiderService;

public class RideApp {
    private final DriverManagement driverManagement;
    private final RiderService riderService;

    public RideApp() {
        LocationRepo locationRepo = new LocationRepo();
        DriverRepo driverRepo = new DriverRepo();
        LockManager lockManager = new LockManager();

        DriverService driverService = new DriverService(driverRepo, locationRepo, lockManager);
        LocationService locationService = new LocationService(locationRepo, driverRepo, lockManager);

        this.riderService = new RiderService(locationRepo);
        this.driverManagement = new DriverManagement(locationService, driverService);
    }

    public DriverManagement getDriverManagement() {
        return this.driverManagement;
    }

    public RiderService getRiderService() {
        return riderService;
    }
}
