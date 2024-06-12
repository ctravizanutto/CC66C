package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import utfpr.cc66c.client.controllers.views.DashboardController;

import java.io.IOException;

public class CandidateDashboardFactory {
    private static Node sidebar;
    private static Node blank;
    private static Node profile;

    public static Scene getScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getCandidateProfileNode());
        DashboardController.getInstance().setLeft(getCandidateSidebarNode());
        return scene;
    }

    private static Node getCandidateProfileNode() {
        if (profile == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/profile-candidate.fxml"));
            try {
                profile = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return profile;
    }

    private static Node getBlankPane() {
        if (blank == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/blank.fxml"));
            try {
                blank = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return blank;
    }

    private static Node getCandidateSidebarNode() {
        if (sidebar == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/sidebar-candidate.fxml"));
            try {
                sidebar = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sidebar;
    }

}