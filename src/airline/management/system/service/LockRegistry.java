package airline.management.system.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class LockRegistry {
    private final ConcurrentMap<String, Object> flightLockRegistry = new ConcurrentHashMap<>();
    private static final LockRegistry LOCK_REGISTRY_INSTANCE = new LockRegistry();
    private LockRegistry(){}

    public static LockRegistry getLockRegistryInstance() {
        return LOCK_REGISTRY_INSTANCE;
    }

    public Object getLock(String flightId) {
        return flightLockRegistry.computeIfAbsent(flightId, (id) -> new Object());
    }
}
