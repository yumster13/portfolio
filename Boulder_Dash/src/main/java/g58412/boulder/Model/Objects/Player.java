package g58412.boulder.model.Objects;

public class Player extends EmptySquare {

    private char color = 'P';
    private final String couleur = "\u001B[41m";
    private String image = "src/img/player.png";

    /**
     * Constructor for the player
     * @param pos the position of the player on the board
     */
    public Player(Position pos){
        super(pos);
    }

    /**
     * Getter for the color of the player
     * @return the color of the player
     */
    public char getColor() {
        return color;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }
}
