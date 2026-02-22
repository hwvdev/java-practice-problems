package snake.ladder.game.entity;

public class Cell implements Cloneable {
    private final int position;
    private Jump jump;

    public Cell(int position) {
        this.position = position;
        this.jump = null;
    }

    public Cell(int position, Jump jump) {
        this.position = position;
        this.jump = jump;
    }

    public void setJump(Jump jump) {
        this.jump = new Jump(jump.getStart(), jump.getEnd());
    }

    public int getPosition() {
        return position;
    }

    public Jump getJump() {
        if (jump!=null)
            return jump.clone();
        return null;
    }

    @Override
    protected Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // This should never happen since we implement Cloneable
        }
    }
}
