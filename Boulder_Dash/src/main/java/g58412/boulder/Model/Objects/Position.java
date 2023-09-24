package g58412.boulder.model.Objects;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    /**
     * Constructor for a position on the board
     * @param x the x value of the position
     * @param y the y value of the position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x value of the position
     * @return the x value of the position
     */
    public int getX() {
        return x;
    }
    /**
     * Getter for the y value of the position
     * @return the y value of the position
     */
    public int getY() {
        return y;
    }

    /**
     * The next position on the board, depends on the direction
     * @param dir a direction of the Direction Enumeration
     * @return the next position depending on the direction
     */
    public Position next(Direction dir){
        return new Position(this.getX()+dir.getDeltaX(), this.getY()+dir.getDeltaY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
