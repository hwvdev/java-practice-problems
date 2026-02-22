package snake.ladder.game.entity.dice;

import java.util.Random;

public class OneDice implements Dice {

    private final Random random = new Random();
    private final int faces;

    public OneDice() {
        this.faces = 6;
    }

    @Override
    public int roll() {
        return random.nextInt(faces)+1;
    }
}
