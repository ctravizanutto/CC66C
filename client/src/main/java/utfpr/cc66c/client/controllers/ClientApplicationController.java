package utfpr.cc66c.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientApplicationController {
    @FXML
    public Label textLabel;

    @FXML
    public TextField textField;

    public void sendText(ActionEvent ignoredEvent) {
        var message = textField.getText();
        message = ClientController.echoText(message);
        textLabel.setText(message);
    }
}