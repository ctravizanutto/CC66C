package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import utfpr.cc66c.client.controllers.views.DashboardController;

import java.io.IOException;

public class RecruiterDashboardFactory {
    private static Node sidebar;
    private static Node profile;

    public static Scene getScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getRecruiterProfileNode());
        DashboardController.getInstance().setLeft(getRecruiterSidebarNode());
        return scene;
    }

    private static Node getRecruiterProfileNode() {
        if (profile == null) {
            var fxmlLoader = new FXMLLoader(RecruiterDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/recruiter/profile-recruiter.fxml"));
            try {
                profile = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return profile;
    }

    private static Node getRecruiterSidebarNode() {
        if (sidebar == null) {
            var fxmlLoader = new FXMLLoader(RecruiterDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/recruiter/sidebar-recruiter.fxml"));
            try {
                sidebar = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sidebar;
    }

}
