package ride.sharing.strategy;

import ride.sharing.entities.Cell;
import ride.sharing.entities.Driver;
import ride.sharing.entities.Ride;
import ride.sharing.repo.LocationRepo;
import ride.sharing.service.DispatcherService;

import java.util.concurrent.ConcurrentMap;

public class GridMatching implements RideMatchingStrategy {

    public GridMatching() {
    }

    @Override
    public void searchDriver(int x, int y, LocationRepo locationRepo, DispatcherService dispatcherService, Ride ride) {
        int radius = 2;

        for (int i = 0; i < radius; i++) {
            for (int dx = -i; dx <= i; dx++) {
                for (int dy = -i; dy <= i; dy++) {
                    int newX = x + dx;
                    int newY = y + dy;

                    if (newX < 0 || newY < 0 || newX > radius || newY > radius)
                        continue;

                    Cell cell = new Cell(newX, newY);
                    ConcurrentMap<String, Driver> driversMap = locationRepo.getDriverMap(cell);
                    if (driversMap == null) continue;
                    for (Driver driver : driversMap.values()) {
                        if (driver.isAvailable()) {
                            dispatcherService.publishDriver(ride.getRideId(), driver);
                        }
                    }
                }
            }
        }
    }

}
