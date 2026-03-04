package ride.sharing.service;

import ride.sharing.entities.Driver;
import ride.sharing.entities.Ride;
import ride.sharing.repo.LocationRepo;
import ride.sharing.strategy.GridMatching;
import ride.sharing.strategy.RideMatchingStrategy;

public class RiderService {
    private final LocationRepo locationRepo;
    private final DispatcherService dispatcherService;
    private RideMatchingStrategy rideMatchingStrategy;
    private final int retry = 3;

    public RiderService(LocationRepo locationRepo, DispatcherService dispatcherService) {
        rideMatchingStrategy = new GridMatching();
        this.locationRepo = locationRepo;
        this.dispatcherService = dispatcherService;
    }

    public void setRideMatchingStrategy(RideMatchingStrategy rideMatchingStrategy) {
        this.rideMatchingStrategy = rideMatchingStrategy;
    }

    public Ride getRide(Ride ride) {
        while (!Thread.currentThread().isInterrupted() && !ride.isConfirmed()) {
            // 🔥 Re-search each iteration
            rideMatchingStrategy.searchDriver(ride.getRideLocation().x(), ride.getRideLocation().y(), locationRepo, dispatcherService, ride);
            try {
                Driver driverAcceptedRide = dispatcherService.pollDriverResponse(ride.getRideId());
                if (driverAcceptedRide != null) {
                    boolean ok = ride.confirmed(driverAcceptedRide.getDriverId());
                    if (ok) {
                        return ride;
                    }
                }
                // Wait before retry
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
