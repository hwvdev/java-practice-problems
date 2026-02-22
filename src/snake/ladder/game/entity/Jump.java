package snake.ladder.game.entity;

public class Jump implements Cloneable {
    private final int start;
    private final int end;

    public Jump(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public Jump clone() {
        try {
            return (Jump) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // This should never happen since we implement Cloneable
        }
    }
}
