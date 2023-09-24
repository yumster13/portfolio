package g58412.boulder.model.Objects;

public class Sand extends EmptySquare{

    private char color = '~';
    private String couleur = "\u001B[43m";
    private String image = "src/img/sand.png";
    /**
     * Constructor for a square of the game
     * @param pos the position of this square
     */
    public Sand(Position pos){
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
