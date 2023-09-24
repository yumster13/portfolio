package g58412.boulder.model.Objects;

import java.util.Objects;

public class EmptySquare {

    private Position pos;
    private char color = ' ';
    private String couleur;

    /**
     * The constructor for an empty square of the game
     * @param pos the position of this empty square
     */
    public EmptySquare(Position pos) {
        this.pos = pos;

    }

    /**
     * Sets the color of the square -> used for the console version of the game
     * @param color the color you want to set
     */
    public void setColor(char color) {
        this.color = color;
        this.couleur = "\u001B[40m";
    }

    /**
     * Getter for the position of the square
     * @return the position of the square
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Getter for the color of the square, the character displayed on the screen
     * @return the color of the square
     */
    public char getColor() {
        return color;
    }

    /**
     * Getter for the background color of the square
     * @return the background color of this square
     */
    public String getCouleur() {
        return couleur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmptySquare that)) return false;
        return getColor() == that.getColor() && Objects.equals(getPos(), that.getPos()) && Objects.equals(getCouleur(), that.getCouleur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPos(), getColor(), getCouleur());
    }
}
