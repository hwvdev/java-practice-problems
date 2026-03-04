package ride.sharing.service;

import ride.sharing.entities.Cell;
import ride.sharing.entities.Driver;
import ride.sharing.repo.DriverRepo;
import ride.sharing.repo.LocationRepo;
import ride.sharing.repo.LockManager;

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

        synchronized (lock) {
            Driver driver = driverRepo.getDriverMap().get(driverId);
            Cell prevCell = driver.getCell();
            Cell currCell = new Cell(x, y);
            driver.updateLocation(currCell);

            locationRepo.removeDriverFromCell(prevCell, driverId);
            locationRepo.addDriverToCell(currCell, driver);
        }
    }


}
