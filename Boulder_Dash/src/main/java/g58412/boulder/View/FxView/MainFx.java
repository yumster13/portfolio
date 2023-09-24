package g58412.boulder.View.FxView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox game = new VBox();
        stage.setTitle("Boulder-Dash");
        Scene scene = new Scene(game,960,552);
        scene.setRoot(new StartScreen(scene,stage));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
