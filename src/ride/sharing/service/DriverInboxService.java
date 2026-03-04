package ride.sharing.service;

import ride.sharing.entities.RideStatus;

public class DriverInboxService {
    private final DispatcherService dispatcherService;

    public DriverInboxService(DispatcherService dispatcherService) {
        this.dispatcherService = dispatcherService;
    }

    public void acceptOrDecline(String driverId, RideStatus rideStatus) {
        /*if (rideStatus == RideStatus.CONFIRMED) {
            if(ride.confirmed(driverId)) {
                dispatcherService.publishRideResponse(ride.getRideId(), ride);
            }
        }*/
    }
}
