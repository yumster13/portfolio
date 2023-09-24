package g58412.boulder.View.FxView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DiedScreen extends VBox {
    /**
     * Constructor for the deadScreen game scene
     * @param scene the scene to set
     * @param stage the new stage
     */
    public DiedScreen(Scene scene, Stage stage) {
    Font font = Font.loadFont("file:src/ressources/Boulder Dash 6128.ttf", 25);
        Label died = new Label("You Died");
        Button home = new Button("HOME");
        Button exit = new Button("EXIT");
        HBox DeadHbox = new HBox();
        HBox NextAction = new HBox();
        died.setFont(font);
        died.setTextFill(Color.WHITE);
        home.setTextFill(Color.DARKRED);
        exit.setTextFill(Color.DARKRED);
        DeadHbox.setAlignment(Pos.BASELINE_CENTER);
        NextAction.setAlignment(Pos.BASELINE_CENTER);
        DeadHbox.getChildren().add(died);
        NextAction.getChildren().add(home);
        NextAction.getChildren().add(exit);
        this.getChildren().addAll(DeadHbox, NextAction);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);
        home.setOnAction(actionEvent -> {
                scene.setRoot(new StartScreen(scene,stage));
        });
        exit.setOnAction(actionEvent -> {
                stage.close();
        });
    }
}
