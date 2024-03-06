package utfpr.cc66c.server;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServerApplicationController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}