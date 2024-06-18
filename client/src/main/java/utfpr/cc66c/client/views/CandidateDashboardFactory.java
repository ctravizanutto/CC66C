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
    private static Node skillset;

    public static Scene getBlankScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getBlankPane());
        DashboardController.getInstance().setLeft(getCandidateSidebarNode());
        return scene;
    }

    public static Scene getProfileScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getCandidateProfileNode());
        return scene;
    }

    public static Scene getSkillsetScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getCandidateSkillsetNode());
        return scene;
    }

    private static Node getCandidateProfileNode() {
        if (profile == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/client/profile-candidate.fxml"));
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
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/client/sidebar-candidate.fxml"));
            try {
                sidebar = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sidebar;
    }

    private static Node getCandidateSkillsetNode() {
        if (skillset == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/client/skillset-candidate.fxml"));
            try {
                skillset = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return skillset;
    }

}