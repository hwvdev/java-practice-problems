package snake.ladder.game.service;

import snake.ladder.game.entity.Cell;

public interface BoardServiceInterface {
    Cell getCell(int nextPosition);
}
