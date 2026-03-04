package ride.sharing;

import ride.sharing.entities.Driver;
import ride.sharing.entities.Ride;
import ride.sharing.manager.DriverManagement;
import ride.sharing.service.DispatcherService;
import ride.sharing.service.DriverInboxService;
import ride.sharing.service.RiderService;

public class Controller {
    public static void main(String[] args) {
        RideApp rideApp = new RideApp();
        DriverManagement driverManagement = rideApp.getDriverManagement();
        RiderService riderService = rideApp.getRiderService();

        driverManagement.addDriver("D1", 0, 0);
        driverManagement.addDriver("D2", 2, 2);
        driverManagement.addDriver("D3", 4, 4);

        driverManagement.getDrivers();

        Thread rideTh = new Thread(() -> {
            Ride ride2 = new Ride(1, 0);
            Ride ride1 = riderService.getRide(ride2);
            System.out.println(ride1);
            Driver driver = driverManagement.getDriverById(ride1.getDriverAtomicReference().get());
            if (driver.rideCompleted()) {
                System.out.println("Ride Completed");
            }

        }, "Ride");
        rideTh.start();

        Thread rideThread = new Thread(() -> {
            Ride ride = new Ride(1, 1);
            Ride ride2 = riderService.getRide(ride);
            System.out.println(ride2);
        }, "Ride2");
        rideThread.start();

        Thread rideThread2 = new Thread(() -> {
            Ride ride = new Ride(1, 2);
            Ride ride2 = riderService.getRide(ride);
            System.out.println(ride2);

        }, "Ride2");
        rideThread2.start();
        Thread rideThread3 = new Thread(() -> {
            Ride ride = new Ride(1, 1);
            Ride ride2 = riderService.getRide(ride);
            System.out.println(ride2);
        }, "Ride2");
        rideThread3.start();

        DriverInboxService driverInboxService = rideApp.getDriverInboxService();
        DispatcherService dispatcherService = rideApp.getDispatcherService();
        Thread polling = new Thread(() -> dispatcherService.pollDriver());
        polling.start();

    }
}
