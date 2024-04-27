package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class CandidateDashboardFactory {

    public static Scene getScene() {
        var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/dashboard.fxml"));
        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
