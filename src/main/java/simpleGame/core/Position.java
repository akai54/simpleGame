package simpleGame.core;

/**
 * Data class representing a position on a 2-axis plane.
 */
public class Position {

    private int x;
    private int y;

    /**
     * Create a position.
     * 
     * @param The x coordinate.
     * @param The y coordinate.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Position on the x axis
     */
    public int getX() {
        return x;
    }

    /**
     * Position on the y axis
     */
    public int getY() {
        return y;
    }

    /**
     * Create a new Position object with the same coordinates.
     */
    public Position copy() {
        return new Position(x, y);
    }

    /**
     * Create a Position object whose coordinates are next to the current Position,
     * in one specified direction.
     * 
     * For example, moving (0,0) to the Right gives (1,0), and moving (5,4) Down
     * gives (5,3).
     * 
     * @param d The Direction of the target position.
     * @return A new Position object whose coordinates are next to the current
     *         Position, in the specified direction.
     */
    public Position getPositionNextTo(Direction d) {
        int newx = x;
        int newy = y;

        switch (d) {
        case Up:
            newy++;
            break;
        case Down:
            newy--;
            break;
        case Left:
            newx--;
            break;
        case Right:
            newx++;
            break;
        }

        return new Position(newx, newy);
    }

    /**
     * Tell whether a Position is directly next to another Position.
     * 
     * For example, (0,0) is next to (1,0), and (5,4) is next to (5,3). However,
     * (0,0) is not next to (1,1).
     * 
     * @param otherPosition
     * @return
     */
    public boolean isNextTo(Position otherPosition) {
        return ((Math.abs(this.x - otherPosition.x) == 1) ^ (Math.abs(this.y - otherPosition.y) == 1));
    }

    @Override
    /**
     * Tell if the position is equal to another.
     * @return true if the other object is a Position, and if is has the same coordinates.
     */
    public boolean equals(Object other) {
        if (other instanceof Position) {
            Position other_cast = (Position) other;
            return this.x == other_cast.x && this.y == other_cast.y;
        }
        return false;
    }
}
