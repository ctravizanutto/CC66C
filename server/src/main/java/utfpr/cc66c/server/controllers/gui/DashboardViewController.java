package utfpr.cc66c.server.controllers.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardViewController implements Initializable {
    @FXML
    public Label ipLabel;
    @FXML
    public ListView<String> listView;

    private List<String> connectedClients;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectedClients = new ArrayList<>();
    }

    public Label getIpLabel() {
        return ipLabel;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public List<String> getConnectedClients() {
        return connectedClients;
    }
}
