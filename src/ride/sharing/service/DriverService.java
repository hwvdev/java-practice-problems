package ride.sharing.service;

import ride.sharing.entities.Driver;
import ride.sharing.repo.DriverRepo;
import ride.sharing.repo.LocationRepo;
import ride.sharing.repo.LockManager;

import java.util.concurrent.ConcurrentMap;

public class DriverService {
    private final DriverRepo driverRepo;
    private final LocationRepo locationRepo;
    private final LockManager lockManager;

    public DriverService(DriverRepo driverRepo, LocationRepo locationRepo, LockManager lockManager) {
        this.driverRepo = driverRepo;
        this.locationRepo = locationRepo;
        this.lockManager = lockManager;
    }

    public void addDriver(String driverId, int x, int y) {
        Object lock = lockManager.getDriverLock(driverId);
        synchronized (lock) {
            Driver driver = driverRepo.addDriver(driverId, x, y);
            locationRepo.addDriverToCell(driver.getCell(), driver);
        }
    }

    public Driver getDriverById(String driverId) {
        return driverRepo.getDriverById(driverId);
    }

    public void printDrivers() {
        ConcurrentMap<String, Driver> drivers = driverRepo.getDriverMap();
        for (Driver driver : drivers.values()) {
            System.out.println(driver);
        }
    }
}
