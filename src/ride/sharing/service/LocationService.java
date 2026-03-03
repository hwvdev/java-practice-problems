package ride.sharing.service;

import ride.sharing.entities.Cell;
import ride.sharing.entities.Driver;
import ride.sharing.repo.DriverRepo;
import ride.sharing.repo.LocationRepo;
import ride.sharing.repo.LockManager;
import ride.sharing.strategy.RideMatchingStrategy;

public class LocationService {
    private final LocationRepo locationRepo;
    private final DriverRepo driverRepo;
    private final LockManager lockManager;

    public LocationService(LocationRepo locationRepo, DriverRepo driverRepo, LockManager lockManager) {
        this.driverRepo = driverRepo;
        this.locationRepo = locationRepo;
        this.lockManager = lockManager;
    }

    public void update(String driverId, int x, int y) {
        Object lock = lockManager.getDriverLock(driverId);
        int xx = x / RideMatchingStrategy.gridSize;
        int yy = y / RideMatchingStrategy.gridSize;

        synchronized (lock) {
            Driver driver = driverRepo.getDriverMap().get(driverId);
            Cell prevCell = driver.getCell();
            Cell currCell = new Cell(xx, yy);
            driver.updateLocation(currCell);

            locationRepo.removeDriverFromCell(prevCell, driverId);
            locationRepo.addDriverToCell(currCell, driver);
        }
    }


}
