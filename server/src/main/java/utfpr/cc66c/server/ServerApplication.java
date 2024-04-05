package utfpr.cc66c.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.controllers.gui.ServerApplicationController;

import java.io.IOException;

public class ServerApplication extends Application {
    private static ServerApplicationController serverApplicationController;
    private ServerController serverController;

    public static ServerApplicationController getServerApplicationController() {
        return serverApplicationController;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        serverController.shutdown();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.getResource("clients-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        serverApplicationController = fxmlLoader.getController();
        stage.setTitle("Server");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        serverController = new ServerController();
    }
}