package g58412.boulder.View.FxView;

import g58412.boulder.model.FacadeGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NextLevel  extends VBox {

    FacadeGame facadeGame = new FacadeGame();
    /**
     * Constructor for the nextLevel game scene
     * @param scene the scene to set
     * @param stage the new stage
     */
    public NextLevel(Scene scene, Stage stage) {
        Font font = Font.loadFont("file:src/ressources/Boulder Dash 6128.ttf", 25);
        Label won = new Label("YOU WON");
        Button home = new Button("HOME");
        Button exit = new Button("EXIT");
        Button next = new Button("NEXT LEVEL");
        HBox nextLevel = new HBox();
        HBox NextAction = new HBox();
        won.setFont(font);
        won.setTextFill(Color.WHITE);
        home.setTextFill(Color.DARKRED);
        exit.setTextFill(Color.DARKRED);
        next.setTextFill(Color.DARKRED);
        nextLevel.setAlignment(Pos.BASELINE_CENTER);
        NextAction.setAlignment(Pos.BASELINE_CENTER);
        nextLevel.getChildren().add(won);
        NextAction.getChildren().add(home);
        NextAction.getChildren().add(exit);
        NextAction.getChildren().add(next);
        this.getChildren().addAll(nextLevel, NextAction);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);
        home.setOnAction(actionEvent -> {
            scene.setRoot(new StartScreen(scene,stage));
        });
        exit.setOnAction(actionEvent -> {
            stage.close();
        });
        next.setOnAction(actionEvent -> {
            facadeGame.nextLevel();
            scene.setRoot(new FxGame(scene,stage,facadeGame.getGame().getLevel()+1));
        });
    }
}
