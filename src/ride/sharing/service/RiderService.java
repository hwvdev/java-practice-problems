package ride.sharing.service;

import ride.sharing.entities.Driver;
import ride.sharing.repo.LocationRepo;
import ride.sharing.strategy.GridMatching;
import ride.sharing.strategy.RideMatchingStrategy;

public class RiderService {
    private final RideMatchingStrategy rideMatchingStrategy;

    public RiderService(LocationRepo locationRepo) {
        rideMatchingStrategy = new GridMatching(locationRepo);
    }

    public Driver getRide(int x, int y) throws InterruptedException {
        Driver driver = rideMatchingStrategy.findAndAcquireDriver(x, y);
        if (driver != null) {
            System.out.println("Driver Found: " + driver);
            return driver;
        }
        System.out.println("No driver Found... Retry");
        return null;
    }
}
