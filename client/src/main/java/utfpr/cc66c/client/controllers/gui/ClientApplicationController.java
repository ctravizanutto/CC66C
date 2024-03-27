package utfpr.cc66c.client.controllers.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.ClientConnectionController;

import java.io.IOException;

public class ClientApplicationController {
    private static Stage stage;

    private static ClientConnectionController connectionController;

    private static Scene loginScene;
    private static Scene IPScene;

    public ClientApplicationController(Stage stage) throws IOException {
        ClientApplicationController.stage = stage;

        var fxmlLoader = new FXMLLoader(getClass().getResource("/utfpr/cc66c/client/ip-view.fxml"));
        ClientApplicationController.IPScene = new Scene(fxmlLoader.load());

        fxmlLoader = new FXMLLoader(getClass().getResource("/utfpr/cc66c/client/login-view.fxml"));
        ClientApplicationController.loginScene = new Scene(fxmlLoader.load());

        stage.setTitle("Client");
        stage.setResizable(false);
        stage.show();

        stage.setScene(IPScene);

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

