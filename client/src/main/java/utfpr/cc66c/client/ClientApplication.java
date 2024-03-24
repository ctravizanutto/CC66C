package utfpr.cc66c.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.application.Application;
import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.gui.ClientApplicationController;
import utfpr.cc66c.client.models.LoginModel;
import utfpr.cc66c.client.serializers.LoginModelSerializer;

import utfpr.cc66c.core.TestClass;

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
        TestClass test;
        launch();
    }

    public static ClientApplicationController getController() {
        return controller;
    }

    public static void setController(ClientApplicationController controller) {
        ClientApplication.controller = controller;
    }
}