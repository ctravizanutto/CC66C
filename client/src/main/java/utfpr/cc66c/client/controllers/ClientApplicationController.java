package utfpr.cc66c.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientApplicationController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}