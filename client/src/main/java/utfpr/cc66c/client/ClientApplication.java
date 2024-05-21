package utfpr.cc66c.client;

import javafx.application.Application;
import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;

public class ClientApplication extends Application {
    private static ApplicationViewController controller;

    @Override
    public void start(Stage stage) {
        controller = new ApplicationViewController(stage);
    }

    @Override
    public void stop() {
        controller.shutdown();
    }

    public static void main(String[] args) {
        launch();
    }

}