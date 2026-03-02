package chess.game.entity;

import chess.game.entity.piece.*;

public class Board {
    private final Cell[][] cells = new Cell[8][8];

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Cell(i, j, null);
            }
        }

        for (int j = 0; j < 8; j++) {
            cells[1][j].setPiece(new Pawn(Color.BLACK.name()));
            cells[6][j].setPiece(new Pawn(Color.WHITE.name()));
        }

        cells[0][0].setPiece(new Rook(Color.BLACK.name()));
        cells[0][7].setPiece(new Rook(Color.BLACK.name()));
        cells[7][0].setPiece(new Rook(Color.WHITE.name()));
        cells[7][7].setPiece(new Rook(Color.WHITE.name()));

        cells[0][1].setPiece(new Knight(Color.BLACK.name()));
        cells[0][6].setPiece(new Knight(Color.BLACK.name()));
        cells[7][1].setPiece(new Knight(Color.WHITE.name()));
        cells[7][6].setPiece(new Knight(Color.WHITE.name()));

        cells[0][2].setPiece(new Bishop(Color.BLACK.name()));
        cells[0][5].setPiece(new Bishop(Color.BLACK.name()));
        cells[7][2].setPiece(new Bishop(Color.WHITE.name()));
        cells[7][5].setPiece(new Bishop(Color.WHITE.name()));

        cells[0][3].setPiece(new Queen(Color.BLACK.name()));
        cells[0][4].setPiece(new King(Color.BLACK.name()));
        cells[7][3].setPiece(new Queen(Color.WHITE.name()));
        cells[7][4].setPiece(new King(Color.WHITE.name()));
    }

    public void move(Cell fromCell, Cell toCell) {
        Piece piece = fromCell.getPiece();
        if (piece != null && piece.isValidMove(cells, fromCell, toCell)) {
            toCell.setPiece(piece);
            fromCell.setPiece(null);
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
}
