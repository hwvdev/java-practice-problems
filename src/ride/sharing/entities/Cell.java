package ride.sharing.entities;

public record Cell(int x, int y) {

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
