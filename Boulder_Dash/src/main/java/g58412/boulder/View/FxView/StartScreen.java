package g58412.boulder.View.FxView;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class StartScreen extends VBox {
    /**
     * Constructor for the startScreen game scene
     * @param scene the scene to set
     * @param stage the new stage
     */
    public StartScreen(Scene scene, Stage stage) {
        Font font = Font.loadFont("file:src/ressources/Boulder Dash 6128.ttf", 25);
        final ImageView selectedImage = new ImageView(new Image("file:src/ressources/main page/BD-logo.png"));
        Button start = new Button("START");
        Button exitHome = new Button("EXIT");
        Label move = new Label("Move: Z  Q  S  D");
        move.setFont(font);
        start.setFont(font);
        exitHome.setFont(font);
        Label level = new Label("SELECT YOUR LEVEL");
        start.setTextFill(Color.DARKRED);
        exitHome.setTextFill(Color.DARKRED);
        move.setTextFill(Color.DARKRED);
        level.setTextFill(Color.DARKRED);
        level.setFont(font);
        final ComboBox<Integer> comboBox = new ComboBox<>();
        comboBox.getItems().setAll(1,2,3);
        HBox title = new HBox();
        HBox levels = new HBox();
        HBox selectLevel = new HBox();
        title.getChildren().add(selectedImage);
        title.setAlignment(Pos.BASELINE_CENTER);
        selectLevel.setAlignment(Pos.BASELINE_CENTER);
        selectLevel.getChildren().addAll(level,comboBox,start,exitHome);
        selectLevel.setPadding(new Insets(20, 50, 50, 50));
        this.getChildren().addAll(title,levels,selectLevel,move);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);
        stage.setTitle("Boulder-Dash");
        stage.show();
        start.setOnAction(actionEvent -> {
            scene.setRoot(new FxGame(scene,stage,comboBox.getValue()));
        });
        exitHome.setOnAction(actionEvent -> stage.close());
    }
}
