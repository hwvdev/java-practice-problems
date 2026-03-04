package ride.sharing.manager;

import ride.sharing.entities.Driver;
import ride.sharing.entities.DriverStatus;
import ride.sharing.service.DriverService;
import ride.sharing.service.LocationService;

public class DriverManagement {
    private final DriverService driverService;
    private final LocationService locationService;

    public DriverManagement(LocationService locationService, DriverService driverService) {
        this.driverService = driverService;
        this.locationService = locationService;
    }

    public void addDriver(String driverId, int x, int y) {
        driverService.addDriver(driverId, x, y);
    }

    public Driver getDriverById(String driverId) {
        return driverService.getDriverById(driverId);
    }

    public void changeStatus(String driverId, DriverStatus driverStatus) {
        Driver driver = driverService.getDriverById(driverId);
        switch (driverStatus) {
            case OFFLINE -> {
                if (driver.goOffline())
                    System.out.println("Offline");
                else
                    System.out.println("You are not Online");
            }
            case AVAILABLE -> {
                if (driver.goOnline())
                    System.out.println("Online");
                else
                    System.out.println("You are not offline");
            }
            case BUSY -> {
                if (driver.tryBusy())
                    System.out.println("Busy");
                else
                    System.out.println("You are not available");
            }
            default -> System.out.println("Unknown Status");
        }
    }

    public void updateLocation(String driverId, int x, int y) {
        locationService.update(driverId, x, y);
    }

    public void getDrivers() {
        driverService.printDrivers();
    }
}
