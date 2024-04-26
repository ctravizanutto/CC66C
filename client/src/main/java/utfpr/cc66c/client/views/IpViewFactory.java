package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class IpViewFactory {
    public static Scene getScene() {
        var fxmlLoader = new FXMLLoader(IpViewFactory.class.getResource("/utfpr/cc66c/client/login/ip-view.fxml"));
        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
