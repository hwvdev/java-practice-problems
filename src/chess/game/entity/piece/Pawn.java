package chess.game.entity.piece;

import chess.game.entity.Cell;

public class Pawn extends Piece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell[][] cells, Cell fromCell, Cell toCell) {
        if (this.getColor().equals("white")) {
            if (fromCell.getPosX() == toCell.getPosX() && fromCell.getPosY() - toCell.getPosY() == 1) {
                return true;
            }
        } else {
            if (fromCell.getPosX() == toCell.getPosX() && fromCell.getPosY() - toCell.getPosY() == -1) {
                return true;
            }
        }
        return false;
    }
}
