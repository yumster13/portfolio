package g58412.boulder.model;

import g58412.boulder.model.Objects.Sortie;

public class Level {

    private int levelNumber;
    private int map[][];
    private int nbDiamonds;
    private Sortie sortie;
    private int height;
    private int width;
    private int diamondsOpenExit;

    /**
     * Constructor for a level
     * @param levelNumber the levelnumber
     * @param height the height of the level
     * @param width the width of the level
     * @param nbDiamondsTotal the total amount of diamonds
     * @param diamondsOpenExit the amount of diamonds to open the exit
     * @param sortie the exit of the level
     */
    public Level(int levelNumber, int height, int width, int nbDiamondsTotal,int diamondsOpenExit, Sortie sortie) {
        this.levelNumber = levelNumber;
        this.map = null;
        this.height = height;
        this.width = width;
        this.nbDiamonds = nbDiamondsTotal;
        this.diamondsOpenExit = diamondsOpenExit;
        this.sortie = sortie;
    }

    /**
     * Getter for the map of the level
     * @return the map of the level
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * Getter for the number of diamonds
     * @return the number of diamonds
     */
    public int getNbDiamonds() {
        return nbDiamonds;
    }

    /**
     * Getter for the levelNumber
     * @return the levelNumber
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Set the map of the level
     * @param map the map to set to the level
     */
    public void setMap(int[][] map) {
        this.map = map;
    }

    /**
     * Get the height of the level
     * @return the height of the level
     */
    public int getHeight() {
        return height;
    }
    /**
     * Get the width of the level
     * @return the width of the level
     */
    public int getWidth() {
        return width;
    }
    /**
     * Get the exit of the level
     * @return the exit of the level
     */
    public Sortie getSortie() {
        return sortie;
    }
    /**
     * Get the amount of diamonds to open the exit of the level
     * @return the amount of diamonds to open the exit of the level
     */
    public int getDiamondsOpenExit() {
        return diamondsOpenExit;
    }
}

