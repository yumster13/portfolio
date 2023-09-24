package g58412.boulder.model.Objects;

public enum Direction {
    UP(0,-1),DOWN(0,1),LEFT(-1,0),RIGHT(1,0);
    ;
    private int deltaX;
    private int deltaY;

    /**
     * The constructor for the direction
     * @param deltaX the x value of the distance you want to travel
     * @param deltaY the y value of the distance you want to travel
     */
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Getter for the x value of the distance you want to travel
     * @return the x value of the distance you want to travel
     */
    public int getDeltaX() {
        return deltaX;
    }
    /**
     * Getter for the y value of the distance you want to travel
     * @return the y value of the distance you want to travel
     */
    public int getDeltaY() {
        return deltaY;
    }

}
