package ride.sharing.entities;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Ride {
    private final String rideId;
    private final AtomicReference<RideStatus> rideStatusAtomicReference;
    private final Cell rideLocation;
    private final AtomicReference<String> driverAtomicReference;

    public Ride(int x, int y) {
        this.rideId = UUID.randomUUID().toString();
        this.rideStatusAtomicReference = new AtomicReference<>(RideStatus.SEARCHING);
        this.driverAtomicReference = new AtomicReference<>(null);
        this.rideLocation = new Cell(x, y);
    }

    public boolean confirmed(String driverId) {
        boolean ok = rideStatusAtomicReference.compareAndSet(RideStatus.SEARCHING, RideStatus.CONFIRMED);
        if (ok)
            driverAtomicReference.set(driverId);
        return ok;
    }

    public boolean cancelled(String driverId) {
        boolean ok = rideStatusAtomicReference.compareAndSet(RideStatus.CONFIRMED, RideStatus.CANCELLED);
        if (ok)
            driverAtomicReference.set(null);
        return ok;
    }

    public String getRideId() {
        return rideId;
    }

    public Cell getRideLocation() {
        return rideLocation;
    }

    public AtomicReference<String> getDriverAtomicReference() {
        return driverAtomicReference;
    }

    public boolean isConfirmed() {
        return rideStatusAtomicReference.get() == RideStatus.CONFIRMED;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId='" + rideId + '\'' +
                ", rideLocation=" + rideLocation +
                ", rideStatus=" + rideStatusAtomicReference +
                ", driverId=" + driverAtomicReference +
                '}';
    }
}
