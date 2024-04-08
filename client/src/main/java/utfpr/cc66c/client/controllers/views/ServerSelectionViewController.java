package utfpr.cc66c.client.controllers.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerSelectionViewController implements Initializable {
    @FXML
    public TextField ipTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ipTextField.setText("localhost");
    }

    public void onEnterIP(ActionEvent ignoredE) {
        var addr = ipTextField.getText();
        if (addr == null || addr.isBlank())
            throw new RuntimeException("[ERROR] Invalid ip address.");
        ApplicationViewController.toLogin(ipTextField.getText());
    }
}
