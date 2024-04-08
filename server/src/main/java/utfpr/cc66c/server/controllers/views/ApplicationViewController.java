package utfpr.cc66c.server.controllers.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utfpr.cc66c.server.ServerApplication;

import java.io.IOException;

public class ApplicationViewController {
    private static DashboardViewController dashboardViewController;

    public static void init(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.getResource("clients-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        dashboardViewController = fxmlLoader.getController();
        stage.setTitle("Server");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static DashboardViewController getDashboardController() {
        return dashboardViewController;
    }
}
