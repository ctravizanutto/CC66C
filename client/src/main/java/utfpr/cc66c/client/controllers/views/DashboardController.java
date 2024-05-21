package utfpr.cc66c.client.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardController {
    private final static DashboardController instance = new DashboardController();
    private static Scene scene;
    @FXML
    public BorderPane borderPane;

    public static DashboardController getInstance() {
        return instance;
    }

    public static Scene getScene() {
        if (scene == null) {
            var fxmlLoader = new FXMLLoader(DashboardController.class.getResource("/utfpr/cc66c/client/dashboard/dashboard.fxml"));
            fxmlLoader.setController(getInstance());
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return scene;
    }

    public void setRight(Node node) {
        borderPane.setRight(node);
    }

    public void setLeft(Node node) {
        borderPane.setLeft(node);
    }
}
