package utfpr.cc66c.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import utfpr.cc66c.server.controllers.ServerController;

import java.io.IOException;

public class ServerApplication extends Application {
    private ServerController serverThread;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.getResource("port-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Server");
        stage.setResizable(false);
        stage.setOnHiding( windowEvent -> serverThread.interrupt());
        stage.setScene(scene);
        stage.show();

        serverThread = new ServerController(3000);
        serverThread.start();
    }

    @Override
    public void stop() {
        serverThread.shutdown();
    }

    public static void main(String[] args) {
        var json = new JSONObject();
        json.put("operation", "LOGIN_CANDIDATE");

        var data = new JSONObject();
        data.put("email", "some@email.com");

        var dataArray = new JSONArray();
        dataArray.put(data);
        json.put("data", data);


        System.out.println(json.getJSONArray("data").getJSONObject(0).get("email"));
        System.out.println(json.getJSONObject("data").get("email"));
        launch();
    }
}