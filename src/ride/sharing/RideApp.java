package ride.sharing;

import ride.sharing.manager.DriverManagement;
import ride.sharing.repo.DriverRepo;
import ride.sharing.repo.LocationRepo;
import ride.sharing.repo.LockManager;
import ride.sharing.service.*;

public class RideApp {
    private final DriverManagement driverManagement;
    private final RiderService riderService;
    private final DispatcherService dispatcherService;
    private final DriverInboxService driverInboxService;

    public RideApp() {
        LocationRepo locationRepo = new LocationRepo();
        DriverRepo driverRepo = new DriverRepo();
        LockManager lockManager = new LockManager();

        DriverService driverService = new DriverService(driverRepo, locationRepo, lockManager);
        LocationService locationService = new LocationService(locationRepo, driverRepo, lockManager);

        this.dispatcherService = new DispatcherService();
        this.riderService = new RiderService(locationRepo, dispatcherService);
        this.driverManagement = new DriverManagement(locationService, driverService);
        driverInboxService = new DriverInboxService(dispatcherService);
    }

    public DriverManagement getDriverManagement() {
        return this.driverManagement;
    }

    public RiderService getRiderService() {
        return riderService;
    }

    public DriverInboxService getDriverInboxService() {
        return driverInboxService;
    }

    public DispatcherService getDispatcherService() {
        return dispatcherService;
    }
}
