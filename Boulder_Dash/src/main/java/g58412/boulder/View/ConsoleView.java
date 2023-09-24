package g58412.boulder.View;

import g58412.boulder.model.Board;
import g58412.boulder.model.Objects.Position;

public class ConsoleView {
    public static final String ANSI_RESET = "\u001B[0m";

    public void displayLevel(Board board){
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                System.out.print(board.getSquare(new Position(j,i)).getCouleur()+" "+board.getSquare(new Position(j,i)).getColor()+" "+ANSI_RESET);
            }
            System.out.println();
        }
    }
    public void displayDiamondsCollected(int nbDiamonds){
        System.out.println("You have collected "+nbDiamonds+" Diamonds");
    }
    public void displayDiamondstoToCollect(int nbDiamonds){
        System.out.println("You have to collect "+nbDiamonds+" Diamonds");
    }
    public void displayDiamondsOpenDoor(int nbDiamonds){
        System.out.println("You have to collect "+nbDiamonds+" Diamonds to open the door");
    }
}
