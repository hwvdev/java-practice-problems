package chess.game.entity.piece;

import chess.game.entity.Cell;

public abstract class Piece {
    private final String color;

    public Piece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean isValidMove(Cell[][] cells, Cell fromCell, Cell toCell);
}
