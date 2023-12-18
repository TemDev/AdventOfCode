public class Pair17 {
    private final int x;
    private final int y;
    private final int movesMade;

    private final int direction;
    public int minimumCost;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMovesMade() {
        return movesMade;
    }

    public int getDirection() {
        return direction;
    }

    public Pair17(int x, int y, int movesMade, int direction, int minimumCost) {
        this.x = x;
        this.y = y;
        this.movesMade = movesMade;
        this.direction = direction;
        this.minimumCost = minimumCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair17 pair17)) return false;
        return x == pair17.getX() && y == pair17.getY() && movesMade == pair17.getMovesMade() && direction == pair17.getDirection();
    }

    @Override
    public int hashCode() {
        return x + 200 * y + 200 * 200 * movesMade + 200 * 200 * 10 * direction;

    }

    @Override
    public String toString() {
        return "Pair17{" +
                "x=" + x +
                ", y=" + y +
                ", movesMade=" + movesMade +
                ", direction=" + direction +
                ", minimumCost=" + minimumCost +
                '}';
    }
}
