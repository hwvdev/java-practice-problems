package ride.sharing.strategy;

import ride.sharing.entities.Cell;
import ride.sharing.entities.Driver;
import ride.sharing.repo.LocationRepo;

import java.util.concurrent.ConcurrentMap;

public class GridMatching implements RideMatchingStrategy {
    private final LocationRepo locationRepo;

    public GridMatching(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public Driver findAndAcquireDriver(int x, int y) throws InterruptedException {
        int grid = RideMatchingStrategy.gridSize;

        while (true) {
            Cell[] riderCell = adjacentCells(x, y);
            for (Cell cell : riderCell) {
                // int dist = Math.abs(cell.getX()-x) + Math.abs(cell.getY()-y);
                ConcurrentMap<String, Driver> matchingRide = locationRepo.getDriverMap(cell);
                if (matchingRide == null)
                    continue;
                for (Driver driver : matchingRide.values()) {
                    if (driver.tryBusy()) {
                        return driver;
                    }
                }
            }
            System.out.println("Could not find retrying");
            Thread.sleep(2000);
        }
    }

    private Cell[] adjacentCells(int x, int y) {
        Cell[] cells = new Cell[9];
        cells[0] = new Cell(x, y);
        cells[1] = new Cell(x - 1, y - 1);
        cells[2] = new Cell(x - 1, y + 1);
        cells[3] = new Cell(x, y - 1);
        cells[4] = new Cell(x, y + 1);
        cells[5] = new Cell(x + 1, y);
        cells[6] = new Cell(x + 1, y - 1);
        cells[7] = new Cell(x + 1, y + 1);
        cells[8] = new Cell(x - 1, y);
        return cells;
    }

}
