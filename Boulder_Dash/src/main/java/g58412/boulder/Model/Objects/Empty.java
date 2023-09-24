package g58412.boulder.model.Objects;

public class Empty extends EmptySquare {
    private char color = ' ';
    private String couleur;

    public Empty(Position pos) {
        super(pos);
        this.couleur = "\u001B[40m";
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
