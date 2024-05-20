package utfpr.cc66c.client.controllers.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class IpSelectionViewController implements Initializable {
    @FXML
    public TextField ipTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ipTextField.setText("localhost");
    }

    public void onEnterIP(ActionEvent ignoredE) {
        var addr = ipTextField.getText();
        if (addr == null || addr.isBlank()) {
            System.out.println("[ERROR] Invalid ip address.");
            return;
        }
        ApplicationViewController.toLogin(ipTextField.getText());
    }
}
