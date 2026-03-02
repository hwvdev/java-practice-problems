package chess.game.entity.piece;

import chess.game.entity.Cell;

public class Queen extends Piece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell[][] cells, Cell fromCell, Cell toCell) {
        if (fromCell.getPosX() == toCell.getPosX() && fromCell.getPosY() != toCell.getPosY()) {
            return true;
        }
        if (fromCell.getPosY() == toCell.getPosY() && fromCell.getPosX() != toCell.getPosX()) {
            return true;
        }
        if (Math.abs(fromCell.getPosX() - toCell.getPosX()) == Math.abs(fromCell.getPosY() - toCell.getPosY())) {
            return true;
        }
        return false;
    }
}
