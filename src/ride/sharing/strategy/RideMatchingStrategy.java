package ride.sharing.strategy;

import ride.sharing.entities.Driver;

public interface RideMatchingStrategy {
    int gridSize = 1;

    Driver findAndAcquireDriver(int x, int y) throws InterruptedException;
}
