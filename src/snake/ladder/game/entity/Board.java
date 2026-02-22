package snake.ladder.game.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Board {
    private final Cell[] cells = new Cell[101];
    private final int size;

    public Board(int size, List<Jump> jumps) {
        this.size = size;
        for (int i = 0; i <= size; i++) {
            cells[i] = new Cell(i);
        }
        for(Jump jump: jumps) {
            cells[jump.getStart()].setJump(jump);
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[] getCells() {
        return cells.clone();
    }

    public Cell getCell(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Position must be between 0 and " + size);
        }
        return cells[position].clone();
    }

}
