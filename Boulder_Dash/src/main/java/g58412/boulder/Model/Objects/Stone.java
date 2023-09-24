package g58412.boulder.model.Objects;

public class Stone extends EmptySquare {

    private char color = 'O';
    public String couleur = "\u001B[47m";
    private String image = "src/img/Stone.png";

    /**
     * Constructor for a Stone of the game
     * @param pos the position of a square
     */
    public Stone(Position pos){
        super(pos);
    }

    @Override
    public char getColor() {
        return color;
    }

    @Override
    public String getCouleur() {
        return couleur;
    }
}
