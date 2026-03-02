package chess.game.entity;

import chess.game.entity.piece.Piece;

public class Cell {
    private final int posX;
    private final int posY;
    private Piece piece;

    public Cell(int posX, int posY, Piece piece) {
        this.posX = posX;
        this.posY = posY;
        this.piece = piece;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
