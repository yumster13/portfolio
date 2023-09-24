package g58412.boulder.View.FxView;

import g58412.boulder.model.Board;
import g58412.boulder.model.Objects.Position;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class map extends GridPane  {

    int width = 30;
    int height = 16;
    Image wall = new Image("file:src/ressources/images/wall.png");
    Image sand = new Image("file:src/ressources/images/sand.png");
    Image Stone = new Image("file:src/ressources/images/Stone.png");
    Image player = new Image("file:src/ressources/images/player.gif");
    Image diamond = new Image("file:src/ressources/images/diamond.gif");
    Image empty = new Image("file:src/ressources/images/empty.png");
    Image exit = new Image("file:src/ressources/images/Exit.png");

    public map() {
    }

    /**
     * Displaying the map in the game
     * @param board the board of the game
     */
    public void displayMap(Board board) {
        this.getChildren().clear();
        int x;
        int y;
        x = board.getPlayer().getPos().getX()/26 * 10;
        y = board.getPlayer().getPos().getY()/15 * 7;
        for (int i = y; i < y+height; i++) {
            for (int j = x; j < x+width; j++) {
                ImageView selectedImage = new ImageView();
                switch(board.getSquare(new Position(j,i)).getColor()){
                    case '#':{
                        selectedImage.setImage(wall);
                        this.add(selectedImage,j,i);
                    break;}
                    case '~':{
                        selectedImage.setImage(sand);
                        this.add(selectedImage,j,i);
                        break;}
                    case 'O':{
                        selectedImage.setImage(Stone);
                        this.add(selectedImage,j,i);
                        break;}
                    case 'P':{
                        selectedImage.setImage(player);
                        this.add(selectedImage,j,i);
                        break;}
                    case 'X':
                    case 'N': {
                        selectedImage.setImage(diamond);
                        this.add(selectedImage,j,i);
                        break;}
                    case ' ':{
                        selectedImage.setImage(empty);
                        this.add(selectedImage,j,i);
                        break;}
                    case 'E':{
                        selectedImage.setImage(exit);
                        this.add(selectedImage,j,i);
                        break;
                    }

                }
            }
            System.out.println();
        }
    }

    /**
     * Convert String into images to print in a hbox
     * @param hbox a hbox to put the images
     * @param val the value to modifiy into images
     */
    public void display(HBox hbox, String val){
        hbox.getChildren().clear();
        if(val.length() == 1){
            hbox.getChildren().add(new ImageView(new Image ("file:src/ressources/chiffres/0.png",32,32,true,true)));
        }
        for (int i = 0; i < val.length(); i++) {
            hbox.getChildren().add(new ImageView(new Image ("file:src/ressources/chiffres/"+val.charAt(i)+".png",32,32,true,true)));
        }
        hbox.setPadding(new Insets(0, 10, 0, 10));
    }

}
