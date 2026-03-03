package ride.sharing.entities;

import java.util.concurrent.atomic.AtomicReference;

public class Driver {
    private final String driverId;
    private final AtomicReference<DriverStatus> driverStatusAtomicReference;
    private volatile Cell cell;

    public Driver(String driverId, int x, int y) {
        this.driverId = driverId;
        this.cell = new Cell(x, y);
        this.driverStatusAtomicReference = new AtomicReference<>(DriverStatus.AVAILABLE);
    }

    public void updateLocation(Cell cell) {
        this.cell = cell;
    }

    public boolean tryBusy() {
        return driverStatusAtomicReference.compareAndSet(DriverStatus.AVAILABLE, DriverStatus.BUSY);
    }

    public boolean goOnline() {
        return driverStatusAtomicReference.compareAndSet(DriverStatus.OFFLINE, DriverStatus.AVAILABLE);
    }

    public boolean goOffline() {
        return driverStatusAtomicReference.compareAndSet(DriverStatus.AVAILABLE, DriverStatus.OFFLINE);
    }

    public boolean rideCompleted() {
        return driverStatusAtomicReference.compareAndSet(DriverStatus.BUSY, DriverStatus.AVAILABLE);
    }

    public String getDriverId() {
        return driverId;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", cell=" + cell +
                ", driverStatus=" + driverStatusAtomicReference +
                '}';
    }
}
