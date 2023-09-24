package g58412.boulder.model.Objects;

public class Wall extends EmptySquare {

    private char color = '#';
    private String couleur = "\u001B[40m";
    private String image = "src/img/wall.png";
    /**
     * Constructor for a Wall of the game
     * @param pos the position of the square
     */
    public Wall(Position pos){
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
