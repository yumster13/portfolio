package g58412.boulder.model;

import g58412.boulder.model.Objects.Position;
import g58412.boulder.model.Objects.Sortie;

import java.util.ArrayList;

public class Levels {

    Level lv1 = new Level(1,22,40,18,12, new Sortie(new Position(38, 16)));
    Level lv2 = new Level(2,22,40,22,12, new Sortie(new Position(8, 12)));
    Level lv3 = new Level(3,16,40,22,11, new Sortie(new Position(21, 1)));
    Level test = new Level(0,5,10,1,1, new Sortie(new Position(1, 1)));

    ArrayList<Level> levels = new ArrayList<>();

    /**
     * Constructor for the level
     */
    public Levels() {
        this.levels.add(test);
        this.levels.add(lv1);
        this.levels.add(lv2);
        this.levels.add(lv3);
    }

    /**
     * Getter for the levels
     * @return a list of the levels
     */
    public ArrayList<Level> getLevels(){
        return levels;
    }

    /**
     * Get a level of the list
     * @param i the number of the level in the list
     * @return  the level of the number in the levels
     */
    public Level getLevel(int i) {
        return levels.get(i);
    }
}
