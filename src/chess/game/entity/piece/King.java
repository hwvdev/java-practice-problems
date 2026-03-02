package chess.game.entity.piece;

import chess.game.entity.Cell;

public class King extends Piece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell[][] cells, Cell fromCell, Cell toCell) {
        if (Math.abs(fromCell.getPosX() - toCell.getPosX()) <= 1 && Math.abs(fromCell.getPosY() - toCell.getPosY()) <= 1) {

            return true;
        }
        return false;
    }
}
