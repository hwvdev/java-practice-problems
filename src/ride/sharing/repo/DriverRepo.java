package ride.sharing.repo;

import ride.sharing.entities.Driver;

import java.util.concurrent.ConcurrentHashMap;

public class DriverRepo {
    private final ConcurrentHashMap<String, Driver> driverMap = new ConcurrentHashMap<>();

    public Driver addDriver(String driverId, int x, int y) {
        return driverMap.compute(driverId, (k, v) -> {
            if (v != null) throw new IllegalStateException("Driver already registered");
            return new Driver(driverId, x, y);
        });
    }

    public ConcurrentHashMap<String, Driver> getDriverMap() {
        return driverMap;
    }

    public Driver getDriverById(String driverId) {
        return driverMap.get(driverId);
    }


}
