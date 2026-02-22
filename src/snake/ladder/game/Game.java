package snake.ladder.game;

import snake.ladder.game.entity.Board;
import snake.ladder.game.entity.Cell;
import snake.ladder.game.entity.dice.Dice;
import snake.ladder.game.entity.Player;
import snake.ladder.game.service.BoardService;
import snake.ladder.game.strategy.DiceStrategy;

import java.util.Queue;


public class Game {
    private final BoardService boardService;
    private final DiceStrategy diceStrategy;
    private final Queue<Player> players;
    private final Player winner;

    public void start() {
        while(winner == null) {
            int count = 0;
            int dice = diceStrategy.rollDice();
            int steps = dice;
            while (dice == 6) {
                System.out.println("Rolled a 6! You get an extra turn.");
                count++;
                if (count==3) {
                    System.out.println("Rolled 6 three times, skipping turn.");
                    break;
                }
                dice = diceStrategy.rollDice();
                steps = steps + dice;
            }
            if (count == 3) {
                players.add(players.poll());
                continue;
            }
            Player currentPlayer = players.poll();
            System.out.println(currentPlayer.getName() + " : " + dice);
            int newPosition = currentPlayer.getPos() + steps;
            if (newPosition > boardService.getBoardSize()) {
                System.out.println("" + currentPlayer.getName() + " rolled " + dice + " but cannot move.");
            } else {
                if (newPosition==boardService.getBoardSize()) {
                    Player p1 = players.peek();
                    Player p2 = players.peek();
                    System.out.println(newPosition + " is the winning position.");
                    System.out.println("Congratulations " + currentPlayer.getName() + "! You have won the game!");
                    System.out.println(p1.getName() + " is at position " + p1.getPos());
                    System.out.println(currentPlayer.getName() + " is at position " + currentPlayer.getPos());
                    System.out.println("" + currentPlayer.getName() + " rolled " + dice + " and wins the game!");
                    break;
                }
                Cell cell = boardService.getCell(newPosition);
                if (cell.getPosition() != newPosition) {
                    System.out.println(currentPlayer.getName() + " hit a jump! Moving from " + newPosition + " to " + cell.getPosition());
                } else {
                    System.out.println(currentPlayer.getName() + " moved to position " + newPosition);
                }
                currentPlayer.setPos(cell.getPosition());
            }
            players.add(currentPlayer);
        }
    }

    public Game(GameBuilder builder) {
        this.boardService = builder.boardService;
        this.diceStrategy = builder.diceStrategy;
        this.players = builder.players;
        this.winner = builder.winner;
    }

    public static class GameBuilder {
        private BoardService boardService;
        private DiceStrategy diceStrategy;
        private Queue<Player> players;
        private Player winner;

        public GameBuilder setBoardService(BoardService boardService) {
            this.boardService = boardService;
            return this;
        }

        public GameBuilder setDiceStrategy(DiceStrategy diceStrategy) {
            this.diceStrategy = diceStrategy;
            return this;
        }

        public GameBuilder setPlayers(Queue<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setWinner(Player winner) {
            this.winner = winner;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
