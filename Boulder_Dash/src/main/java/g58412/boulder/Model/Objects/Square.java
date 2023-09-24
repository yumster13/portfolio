package g58412.boulder.model.Objects;

import java.util.Objects;

public class Square {

    private EmptySquare piece;

    /**
     * A square of the board
     * @param piece a piece is an EmptySquare and child classes that can be put on a board
     */
    public Square(EmptySquare piece) {
        this.piece = piece;
    }

    /**
     * Getter for the piece of a square of the board
     * @return the piece of the square on the board
     */
    public EmptySquare getPiece() {
        return piece;
    }

    /**
     * Getter for the piece of a square of the board
     * @param piece the piece to place on a square on the board
     */
    public void setPiece(EmptySquare piece) {
        this.piece = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square square)) return false;
        return Objects.equals(getPiece(), square.getPiece());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPiece());
    }
}
