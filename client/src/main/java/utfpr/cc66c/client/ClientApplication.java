package utfpr.cc66c.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.TCPClientController;

import java.io.IOException;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.show();

        var client = new TCPClientController(3000);
        client.start();
    }

    public static void main(String[] args) {
       launch();
    }
}