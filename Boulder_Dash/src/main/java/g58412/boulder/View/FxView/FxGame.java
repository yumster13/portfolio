package g58412.boulder.View.FxView;

import g58412.boulder.model.FacadeGame;
import g58412.boulder.model.Gamestate;
import g58412.boulder.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class FxGame extends VBox implements Observer{

    map map = new map();
    boolean Open = false;
    private final HBox score = new HBox();
    private final HBox DiamondsTotal = new HBox();
    private final Label DiamondsT = new Label();
    private final Label DiamondsC = new Label();
    private final Label DiamondsOpenDoor = new Label();
    private final HBox OpenDoorDia = new HBox();

    private final FacadeGame facadeGame = new FacadeGame();
    /**
     * Constructor for the FxGame game scene
     * @param scene the scene to set
     * @param stage the new stage
     */
    public FxGame(Scene scene,Stage stage,int value) {
    Font font = Font.loadFont("file:src/ressources/Boulder Dash 6128.ttf", 25);
        facadeGame.startLevel(value);
        map.displayMap(facadeGame.getGame().getBoard());
        facadeGame.addObserver(this);
        HBox undoHbox = new HBox();
        Button undo = new Button("UNDO");
        Button redo = new Button("REDO");
        Button homeGame = new Button("HOME");
        undo.setTextFill(Color.DARKRED);
        redo.setTextFill(Color.DARKRED);
        homeGame.setTextFill(Color.DARKRED);
        homeGame.setFont(font);
        undoHbox.getChildren().addAll(undo,redo,homeGame);
        HBox message = new HBox();
        HBox OpenExitMessage = new HBox();
        ImageView noDirection = new ImageView(new Image("file:src/ressources/images/noDirection.png",40,40,true,true));
        ImageView OpenExit = new ImageView(new Image("file:src/ressources/images/Sortie.png",48,48,true,true));
        message.getChildren().add(noDirection);
        OpenExitMessage.getChildren().add(OpenExit);
        undo.setFont(font);
        redo.setFont(font);
        VBox vbox = new VBox();
        HBox gameInfo = new HBox();
        update();
        gameInfo.getChildren().addAll(score,DiamondsTotal,OpenDoorDia,undoHbox);
        this.getChildren().addAll(gameInfo,map);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(vbox);

        undo.setOnAction(actionEvent -> facadeGame.action("UNDO"));
        redo.setOnAction(actionEvent -> facadeGame.action("REDO"));
        homeGame.setOnAction(actionEvent -> {
            scene.setRoot(new StartScreen(scene,stage));
        });
        this.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent key) -> {
            undoHbox.getChildren().remove(message);
            try {
                if (key.getCode().equals(KeyCode.Z)) {
                    facadeGame.action("U");
                } else if (key.getCode().equals(KeyCode.S)) {
                    facadeGame.action("D");
                } else if (key.getCode().equals(KeyCode.Q)) {
                    facadeGame.action("L");
                } else if (key.getCode().equals(KeyCode.D)) {
                    facadeGame.action("R");
                }
            }catch (Exception e){
                undoHbox.getChildren().add(message);
            }
            if(facadeGame.getGame().isGameState().equals(Gamestate.STOPPED)){
                scene.setRoot(new DiedScreen(scene,stage));
            }
            if(facadeGame.getGame().isGameState().equals(Gamestate.EXIT)){

                if(facadeGame.getGame().getLevel() == 3){
                    scene.setRoot(new FinishedGame(scene,stage));
                }else{
                    scene.setRoot(new NextLevel(scene,stage));
                }
            }
            if(facadeGame.getGame().getBoard().getSortie().isOpen() && !Open){
                Open = true;
                undoHbox.getChildren().add(OpenExitMessage);
            }
        });

    }
    @Override
    public void update() {
        DiamondsT.setText(String.valueOf(facadeGame.getGame().getBoard().nbDiamondstoCollect()));
        DiamondsC.setText(String.valueOf(facadeGame.getGame().getBoard().nbDiamondsCollected()));
        DiamondsOpenDoor.setText(String.valueOf(facadeGame.nbDiamondsOpenDoor()));
        map.display(score,DiamondsC.getText());
        map.display(DiamondsTotal,DiamondsT.getText());
        map.display(OpenDoorDia,DiamondsOpenDoor.getText());
        map.displayMap(facadeGame.getGame().getBoard());
    }


}

