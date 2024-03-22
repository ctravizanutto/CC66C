package utfpr.cc66c.client;

import javafx.application.Application;
import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.gui.ClientApplicationController;

import java.io.IOException;

public class ClientApplication extends Application {
    private static ClientApplicationController controller;

    @Override
    public void start(Stage stage) throws IOException {
        controller = new ClientApplicationController(stage);
    }

    @Override
    public void stop() {
        controller.shutdown();
    }

    public static void main(String[] args) {
       launch();
    }

    public static ClientApplicationController getController() {
        return controller;
    }

    public static void setController(ClientApplicationController controller) {
        ClientApplication.controller = controller;
    }
}