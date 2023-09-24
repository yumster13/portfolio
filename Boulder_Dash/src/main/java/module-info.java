module g58412.boulder.boulderdash {
    requires javafx.controls;
    requires javafx.fxml;


    opens g58412.boulder to javafx.fxml;
    exports g58412.boulder.View;
    exports g58412.boulder.View.FxView;
}