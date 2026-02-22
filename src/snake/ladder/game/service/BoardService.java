package snake.ladder.game.service;

import snake.ladder.game.entity.Board;
import snake.ladder.game.entity.Cell;
import snake.ladder.game.entity.Jump;

public class BoardService implements BoardServiceInterface {
    private final Board board;

    public BoardService(Board board) {
        this.board = board;
    }

    public Cell getCell(int position) {
        Cell cell = board.getCell(position);
        if (cell.getJump()!=null) {
            Jump jump = cell.getJump();
            return board.getCell(jump.getEnd());
        }
        return cell;
    }

    public int getBoardSize() {
        return board.getSize();
    }

}
