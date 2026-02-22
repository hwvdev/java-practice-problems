package snake.ladder.game;

import snake.ladder.game.entity.Board;
import snake.ladder.game.entity.Jump;
import snake.ladder.game.entity.Player;
import snake.ladder.game.entity.dice.OneDice;
import snake.ladder.game.entity.dice.TwoDice;
import snake.ladder.game.service.BoardService;
import snake.ladder.game.strategy.DiceStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int boardSize = 100;
        List<Jump> jumpList = new ArrayList<>();
        jumpList.add(new Jump(33, 22));
        jumpList.add(new Jump(50, 8));
        jumpList.add(new Jump(23, 2));
        jumpList.add(new Jump(20, 9));
        jumpList.add(new Jump(5, 66));
        jumpList.add(new Jump(99, 7));
        jumpList.add(new Jump(77, 54));
        jumpList.add(new Jump(91, 70));
        jumpList.add(new Jump(43, 12));


        Queue<Player> players = new java.util.LinkedList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));

        Board board = new Board(boardSize, jumpList);
        Game game = new Game.GameBuilder()
                .setBoardService(new BoardService(board))
                .setDiceStrategy(new DiceStrategy(new OneDice()))
                .setPlayers(players)
                .setWinner(null)
        .build();

        game.start();
    }
}
