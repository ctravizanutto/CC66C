package utfpr.cc66c.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.ClientController;
import utfpr.cc66c.client.views.ClientApplicationView;

import java.io.IOException;

public class ClientApplication extends Application {
    private ClientController client;
    private static ClientApplicationView clientApplicationView;

    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        clientApplicationView = fxmlLoader.getController();

        client = new ClientController(21234);
    }

    @Override
    public void stop() {
        client.shutdown();
    }

    public static void main(String[] args) {
       launch();
    }

    public static ClientApplicationView getClientApplicationView() {
        return clientApplicationView;
    }
}