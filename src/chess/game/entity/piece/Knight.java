package chess.game.entity.piece;

import chess.game.entity.Cell;

public class Knight extends Piece {
    public Knight(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell[][] cells, Cell fromCell, Cell toCell) {
        if ((Math.abs(fromCell.getPosX() - toCell.getPosX()) == 2 && Math.abs(fromCell.getPosY() - toCell.getPosY()) == 1) ||
                (Math.abs(fromCell.getPosX() - toCell.getPosX()) == 1 && Math.abs(fromCell.getPosY() - toCell.getPosY()) == 2)) {
            return true;
        }
        return false;
    }
}
