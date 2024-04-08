package utfpr.cc66c.client.controllers.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.ClientConnectionController;

import java.io.IOException;

public class ApplicationViewController {
    private static Stage stage;

    private static ClientConnectionController connectionController;

    private static Scene loginScene;
    private static Scene severSelectionScene;
    public static LoginViewController loginController;

    public ApplicationViewController(Stage stage) throws IOException {
        ApplicationViewController.stage = stage;

        var fxmlLoader = new FXMLLoader(getClass().getResource("/utfpr/cc66c/client/ip-view.fxml"));
        ApplicationViewController.severSelectionScene = new Scene(fxmlLoader.load());

        fxmlLoader = new FXMLLoader(getClass().getResource("/utfpr/cc66c/client/login-view.fxml"));
        ApplicationViewController.loginScene = new Scene(fxmlLoader.load());
        loginController = fxmlLoader.getController();

        stage.setTitle("Client");
        stage.setResizable(false);
        stage.show();

        stage.setScene(severSelectionScene);
    }

    public void shutdown() {
        if (connectionController != null)
            connectionController.shutdown();
    }

    public static void toLogin(String addr) {
        connectionController = new ClientConnectionController(addr, 21234);
        stage.setScene(loginScene);
    }

}

