package g58412.boulder.model.Objects;


public class Sortie extends EmptySquare {

    private char color;
    private boolean isOpen;
    public String couleur;
    private String image = "src/img/exit.png";

    /**
     * Constructor for an Exit in the game
     * @param pos the position of this exit
     */
    public Sortie(Position pos){
        super(pos);
        this.color = '~';
        this.couleur= "\u001B[43m";
        this.isOpen = false;
    }


    @Override
    public char getColor() {
        return color;
    }

    /**
     * Getter for the isOpen attribute
     * @return true if open, else otherwise
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Setter for the isOpen attribute of the exit
     * @param open a boolean to set the exit to true when open
     */
    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void setColor(char color) {
        this.color = color;
        this.couleur = "\u001B[45m";
    }

    @Override
    public String getCouleur() {
        return couleur;
    }
}
