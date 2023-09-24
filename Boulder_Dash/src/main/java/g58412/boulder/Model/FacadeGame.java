package g58412.boulder.model;

import g58412.boulder.util.Observable;
import g58412.boulder.util.Observer;

import java.util.ArrayList;
import java.util.List;

public class FacadeGame implements Observable {

    private final Game game;
    private final List<Observer> observers = new ArrayList<>();

    public FacadeGame() {
        this.game = new Game();
    }

    /**
     * Enables the game to start, to create the current level
     * @param n the number of the level you want to start
     */
    public void startLevel(int n){
        game.startLevel(n);
    }
    /**
     * Enables to check if the player is dead or the player exited the game
     * @return a boolean to check if the game is finished
     */
    public boolean isFinished(){
        return game.isGameState().equals(Gamestate.STOPPED);
    }

    /**
     * Checks if the action is valid
     * Choices: UNDO, REDO, R, L, U, D
     * @param action the action the player wants to execute
     */
    public void action(String action){
        action = action.toUpperCase();
        switch(action){
            case "UNDO","REDO","U","D","L","R" -> {
                game.moveExecute(action);
                this.notifyObservers();
            }
            default -> throw new IllegalArgumentException("This is not a valid direction");
        }

    }

    /**
     * Getter for the Game
     * @return the object game, the current playing game
     */
    public Game getGame() {
        return game;
    }



    /**
     * The amount of diamonds that you can collect
     * @return the amount of diamonds to collect on the board
     */
    public int nbDiamondsToCollect(){
        this.notifyObservers();
        return game.getBoard().nbDiamondstoCollect();
    }

    /**
     * The amount of diamonds collected by the player
     * @return the amout of diamonds collected by the player
     */
    public int nbDiamondsCollected(){
        this.notifyObservers();
        return game.getBoard().nbDiamondsCollected();
    }

    /**
     * The amount of diamonds to Open the door
     * @return the amount of diamonds to open the door
     */
    public int nbDiamondsOpenDoor(){
        return game.getLevels().getLevel(game.getLevel()).getDiamondsOpenExit();
    }

    /**
     * Function able to go to the next level
     */
    public void nextLevel(){
            this.getGame().nextLevel();
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }
}
