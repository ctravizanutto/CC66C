package utfpr.cc66c.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utfpr.cc66c.server.controllers.ServerController;

import java.io.IOException;

public class ServerApplication extends Application {
    private ServerController serverThread;

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.getResource("port-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//
//        stage.setTitle("Server");
//        stage.setResizable(false);
//        stage.setScene(scene);
        //stage.show(); DEBUG

    }

    @Override
    public void stop() {
        serverThread.shutdown();
    }

    public static void main(String[] args) {
//        launch();

    new ServerController();
    }
}