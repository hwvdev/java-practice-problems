package ride.sharing;

import ride.sharing.entities.Driver;
import ride.sharing.manager.DriverManagement;
import ride.sharing.service.RiderService;

public class Controller {
    public static void main(String[] args) throws InterruptedException {
        RideApp rideApp = new RideApp();
        DriverManagement driverManagement = rideApp.getDriverManagement();
        RiderService riderService = rideApp.getRiderService();

        driverManagement.addDriver("D1", 0, 0);
        driverManagement.addDriver("D2", 2, 2);
        driverManagement.addDriver("D3", 4, 4);

        driverManagement.getDrivers();

//        driverManagement.changeStatus("D1", DriverStatus.OFFLINE);
        //      driverManagement.changeStatus("D1", DriverStatus.AVAILABLE);
        //  driverManagement.getDrivers();
        System.out.println();
        Driver driver = riderService.getRide(1, 1);
        System.out.println(driver);
        Driver driver1 = riderService.getRide(1, 1);
        System.out.println(driver1);
        Driver driver3 = riderService.getRide(1, 2);
        Driver driver4 = riderService.getRide(1, 2);
    }
}
