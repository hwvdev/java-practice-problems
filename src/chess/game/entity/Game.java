package chess.game.entity;

import snake.ladder.game.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player winner;
    private int turn = 0;

    public Game(Board board, Player whitePlayer, Player blackPlayer) {
        this.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.winner = null;
    }

    public void start() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(winner==null) {
            if (turn==0) {
                try {
                    int fromX = Integer.parseInt(br.readLine());
                    int fromY = Integer.parseInt(br.readLine());

                    int toX = Integer.parseInt(br.readLine());
                    int toY = Integer.parseInt(br.readLine());

                    Cell fromCell = board.getCell(fromX, fromY);
                    Cell toCell = board.getCell(toX, toY);
                    board.move(fromCell, toCell);
 //                   implementCheckmate logic suggested by chatgpt
                    turn = 1;


                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter valid coordinates.");
                }
            } else {

            }
        }
    }
}
