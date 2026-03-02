package chess.game.entity.piece;

import chess.game.entity.Cell;

public class Rook extends Piece {
    public Rook(String color) {
        super(color);
    }

    public boolean isValidMove(Cell[][] cells, Cell fromCell, Cell toCell) {
        if (fromCell.getPosX() == toCell.getPosX() && fromCell.getPosY() != toCell.getPosY()) {
            return true;
        }
        if (fromCell.getPosY() == toCell.getPosY() && fromCell.getPosX() != toCell.getPosX()) {
            return true;
        }
        return false;
    }
}
