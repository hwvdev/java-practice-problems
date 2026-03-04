package ride.sharing.service;

import ride.sharing.entities.Driver;
import ride.sharing.entities.RideStatus;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class DispatcherService {
    private final Map<String, BlockingQueue<Driver>> rideRequestQueue = new ConcurrentHashMap<>();
    private final Map<String, BlockingQueue<Driver>> rideResponseQueue = new ConcurrentHashMap<>();

    public DispatcherService() {
    }

    public void publishDriver(String rideId, Driver driver) {
        rideRequestQueue.compute(rideId, (k, v) -> {
            if (v == null)
                v = new LinkedBlockingDeque<>();
            v.add(driver);
            return v;
        });
        System.out.println("Driver published");
    }

    public void pollDriver() {
        while (true) {
            try {
                for (Map.Entry<String, BlockingQueue<Driver>> rideIdDriverQueue : rideRequestQueue.entrySet()) {
                    String rideId = rideIdDriverQueue.getKey();
                    BlockingQueue<Driver> drivers = rideIdDriverQueue.getValue();

                    if (drivers == null) continue;

                    Driver driver = drivers.poll(200, TimeUnit.MILLISECONDS);
                    if (driver == null || !driver.isAvailable())
                        continue;
                    System.out.println("accept or reject ride: " + driver);

                    // simulate accept or decline of request
                    RideStatus rideStatus = null;
                    Random random = new Random();
                    int i = random.nextInt(0, 2);
                    // if (i==0) {
                    rideStatus = RideStatus.CONFIRMED;
                    if (driver.tryBusy()) {
                        publishRideResponse(rideId, driver);
                        rideRequestQueue.remove(rideId);
                    }
                    //}
                    //else {
                    //System.out.println("Driver Rejected Ride");
                    //  rideStatus = RideStatus.CANCELLED;
                    //}
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void publishRideResponse(String rideId, Driver driver) {
//        System.out.println("Driver accepted ride: "+rideId);
        rideResponseQueue.compute(rideId, (k, v) -> {
            if (v == null)
                v = new LinkedBlockingDeque<>();
            v.add(driver);
            return v;
        });
    }

    public Driver pollDriverResponse(String rideId) {
//        System.out.println("Polling accepted ride");
        try {
            if (rideResponseQueue.get(rideId) == null)
                return null;
            return rideResponseQueue.get(rideId).poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
