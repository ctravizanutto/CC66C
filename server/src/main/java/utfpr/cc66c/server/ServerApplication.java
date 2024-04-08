package utfpr.cc66c.server;

import javafx.application.Application;
import javafx.stage.Stage;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.controllers.views.ApplicationViewController;

import java.io.IOException;

public class ServerApplication extends Application {
    private ServerController serverController;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        serverController.shutdown();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ApplicationViewController.init(stage);
        serverController = new ServerController();
    }
}