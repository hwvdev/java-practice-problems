package snake.ladder.game.strategy;

import snake.ladder.game.entity.dice.Dice;

public class DiceStrategy {
    private Dice dice;

    public DiceStrategy(Dice dice) {
        this.dice = dice;
    }

    public int rollDice() {
        return dice.roll();
    }
}
