package ride.sharing.strategy;

import ride.sharing.entities.Ride;
import ride.sharing.repo.LocationRepo;
import ride.sharing.service.DispatcherService;

public interface RideMatchingStrategy {
    void searchDriver(int x, int y, LocationRepo locationRepo, DispatcherService dispatcherService, Ride ride);
}
