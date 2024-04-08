package utfpr.cc66c.server.controllers.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerApplicationController implements Initializable {
    @FXML
    public Label ipLabel;
    @FXML
    public ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().add("hello");
    }
}
