class SudokuSolver {
    public void solveSudoku(char[][] board) {
        solve(board,0,0);
    }

    private boolean solve(char[][] board, int row, int col) {
        if (row == board.length && col == board[0].length) {
            return true;
        }
        if (col == board.length) {
            return solve(board, row+1, 0);
        }
        if (row == board.length) {
            return true;
        }

        if (board[row][col] == '.') {
            for(char ch = '1'; ch <= '9'; ch++) {
                if (isValidSudoku(board, row, col, ch)) {
                    board[row][col] = ch;
                    boolean valid = solve(board,row,col+1);
                    if (!valid) {
                        board[row][col] = '.';
                    } else
                        return true;
                }
            }
            return false;
        } else
            return solve(board, row, col+1);
    }

    boolean isValidSudoku(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch) return false;
            if (board[i][col] == ch) return false;
        }
        int r1 = row / 3 * 3;
        int c1 = col / 3 * 3;
        for (int i = r1; i < r1+3; i++) {
            for (int j = c1; j < c1+3; j++) {
                if (board[i][j] == ch) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        char[][] board = new char[9][9];

        board[0] = new char[]{'.','.','8','4','.','.','9','.','.'};
        board[1] = new char[]{'.','.','.','5','.','.','4','7','.'};
        board[2] = new char[]{'9','.','2','.','.','.','.','.','5'};
        board[3] = new char[]{'2','.','3','.','.','.','.','.','.'};
        board[4] = new char[]{'.','.','.','6','.','9','.','.','4'};
        board[5] = new char[]{'.','1','.','.','.','.','.','9','7'};
        board[6] = new char[]{'.','.','.','.','8','.','.','.','.'};
        board[7] = new char[]{'.','8','.','.','.','.','3','6','.'};
        board[8] = new char[]{'.','6','1','.','2','3','.','8','.'};

        solver.solveSudoku(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}