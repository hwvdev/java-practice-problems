package ride.sharing.repo;

import ride.sharing.entities.Cell;
import ride.sharing.entities.Driver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocationRepo {
    private final ConcurrentMap<Cell, ConcurrentMap<String, Driver>> locationMap = new ConcurrentHashMap<>();

    public ConcurrentMap<Cell, ConcurrentMap<String, Driver>> getLocationMap() {
        return locationMap;
    }

    public ConcurrentMap<String, Driver> getDriverMap(Cell cell) {
        return locationMap.get(cell);
    }

    public void addDriverToCell(Cell cell, Driver driver) {
        locationMap.compute(cell, (k, v) -> {
            if (v == null) {
                v = new ConcurrentHashMap<>();
            }
            v.put(driver.getDriverId(), driver);
            return v;
        });
    }

    public void removeDriverFromCell(Cell cell, String driverId) {
        locationMap.computeIfPresent(cell, (k, v) -> {
            v.remove(driverId);
            return v.isEmpty() ? null : v;
        });
    }
}
