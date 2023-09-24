package g58412.boulder.model.Objects;

public class Diamond extends EmptySquare {

    private char color = 'X';
    private String couleur = "\u001B[46m";
    private String image = "src/img/diamond.png";
    /**
     * Constructor for a Diamond of the game
     * @param pos the position of this square
     */
    public Diamond(Position pos){
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
