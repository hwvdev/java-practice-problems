package airline.management.system.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class LockRegistry {
    private final ConcurrentMap<String, Object> flightLockRegistry = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Object> flightServiceLock = new ConcurrentHashMap<>();

    private static final LockRegistry LOCK_REGISTRY_INSTANCE = new LockRegistry();
    private LockRegistry(){}

    public static LockRegistry getLockRegistryInstance() {
        return LOCK_REGISTRY_INSTANCE;
    }

    public Object getLock(String flightSeatId) {
        return flightLockRegistry.computeIfAbsent(flightSeatId, (id) -> new Object());
    }

    public Object getFlightServiceLock(String flightId) {
        return flightServiceLock.computeIfAbsent(flightId, (id) -> new Object());
    }
}
