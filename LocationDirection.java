public class LocationDirection {
    private int x;
    private int y;

    // 0 for moving east, 1 for moving south, 2 for moving west and 3 for moving north
    private int direction;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }


    public LocationDirection(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;

    }

    public void moveForward() {
        if (direction == 0) {
            x += 1;
        } else if (direction == 1) {
            y += 1;
        } else if (direction == 2) {
            x -= 1;
        } else {
            y -= 1;
        }
    }

    public void forwardSlash() {
        if (direction == 0) {
            direction = 3;
        } else if (direction == 1) {
            direction = 2;
        } else if (direction == 2) {
            direction = 1;
        } else {
            direction = 0;
        }
    }

    public void backSlash() {
        if (direction == 0) {
            direction = 1;
        } else if(direction == 1) {
            direction = 0;
        } else if (direction == 2) {
            direction = 3;
        } else {
            direction = 2;
        }
    }
}
