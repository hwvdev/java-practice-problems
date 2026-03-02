package chess.game.entity.piece;

import chess.game.entity.Cell;

public class Bishop extends Piece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Cell[][] cells, Cell fromCell, Cell toCell) {
        if (Math.abs(fromCell.getPosX() - toCell.getPosX()) == Math.abs(fromCell.getPosY() - toCell.getPosY())) {
            int distance = Math.abs(fromCell.getPosX() - toCell.getPosX());
            // find if there is any piece in the way
            for (int i = 1; i < distance; i++) {
                int x = fromCell.getPosX() + i * (toCell.getPosX() - fromCell.getPosX()) / distance;
                int y = fromCell.getPosY() + i * (toCell.getPosY() - fromCell.getPosY()) / distance;
                if (cells[x][y].getPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}
