package g58412.boulder.controller;

import g58412.boulder.View.ConsoleView;
import g58412.boulder.model.FacadeGame;
import g58412.boulder.model.Gamestate;

import java.util.Scanner;

public class Controller {

    private final FacadeGame facade;
    private final g58412.boulder.View.ConsoleView vue;

    public Controller() {
        this.facade = new FacadeGame();
        this.vue = new ConsoleView();
    }

    public void play(){
        Scanner kb = new Scanner(System.in);
        System.out.println("What level do you want to play?");
        int level = kb.nextInt();
        facade.startLevel(level);
        vue.displayLevel(facade.getGame().getBoard());
        do{
            try {
                System.out.println("What direction do you want to go? UNDO REDO U D L R");
                String command = kb.next();
                facade.action(command);
                if(facade.getGame().isGameState().equals(Gamestate.EXIT)){
                    facade.nextLevel();
                }
                vue.displayDiamondsCollected(facade.nbDiamondsCollected());
                vue.displayDiamondsOpenDoor(facade.nbDiamondsOpenDoor());
                vue.displayDiamondstoToCollect(facade.nbDiamondsToCollect());
                vue.displayLevel(facade.getGame().getBoard());
            } catch (Exception j) {
                System.out.println(j.getMessage());
            }
        }while(!facade.isFinished());
    }
}
