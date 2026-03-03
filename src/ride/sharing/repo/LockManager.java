package ride.sharing.repo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LockManager {
    private final ConcurrentMap<String, Object> driverLock = new ConcurrentHashMap<>();

    public Object getDriverLock(String driverId) {
        return driverLock.computeIfAbsent(driverId, k -> new Object());
    }

}
