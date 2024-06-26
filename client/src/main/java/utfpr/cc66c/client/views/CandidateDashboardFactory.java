package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import utfpr.cc66c.client.controllers.views.DashboardController;
import utfpr.cc66c.client.controllers.views.candidate.CandidateSkillController;
import utfpr.cc66c.client.controllers.views.candidate.JobsetCandidateViewController;
import utfpr.cc66c.client.controllers.views.candidate.SkillsetCandidateViewController;

import java.io.IOException;

public class CandidateDashboardFactory {
    private static Node sidebar;
    private static Node profile;
    private static Node skillset;
    private static Node skill;
    private static Node jobset;
    private static Node companyset;
    private static CandidateSkillController skillController;
    private static SkillsetCandidateViewController viewSkillsetController;
    private static JobsetCandidateViewController viewJobsetController;

    public static Scene getProfileScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getCandidateProfileNode());
        return scene;
    }

    public static Scene getJobsetScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getJobsetNode());
        return scene;
    }

    public static Scene getSkillsetScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getCandidateSkillsetNode());
        return scene;
    }

    public static Scene getSkillAddScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getCandidateSkillAdd());
        skillController.deleteButton.setDisable(true);
        skillController.skillChoiceBox.setValue(null);
        skillController.experienceTextField.setText(null);
        skillController.experienceTextField.setDisable(false);
        return scene;
    }

    public static Scene getSkillEditScene(String experience, String oldSkill) {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getCandidateSkillAdd());
        skillController.deleteButton.setDisable(false);
        skillController.skillChoiceBox.setValue(oldSkill);
        skillController.experienceTextField.setText(experience);
        skillController.experienceTextField.setDisable(true);
        skillController.oldSkill = oldSkill;
        return scene;
    }

    public static Scene getCompanysetScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setLeft(getCandidateSidebarNode());
        DashboardController.getInstance().setRight(getCompanyNode());
        return scene;
    }

    public static void updateSkillsetView() {
        viewSkillsetController.updateList();
    }

    public static void updateJobsetView(boolean andFilter) {
        viewJobsetController.updateList(andFilter);
    }

    private static Node getCandidateProfileNode() {
        if (profile == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/candidate/profile-candidate.fxml"));
            try {
                profile = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return profile;
    }

    private static Node getCandidateSidebarNode() {
        if (sidebar == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/candidate/sidebar-candidate.fxml"));
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
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/candidate/skillset-candidate.fxml"));
            try {
                skillset = fxmlLoader.load();
                viewSkillsetController = fxmlLoader.getController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return skillset;
    }

    private static Node getCandidateSkillAdd() {
        if (skill == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/candidate/skill-candidate.fxml"));
            try {
                skill = fxmlLoader.load();
                skillController = fxmlLoader.getController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return skill;
    }

    private static Node getJobsetNode() {
        if (jobset == null) {
            var fxmlLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/candidate/jobset-candidate.fxml"));
            try {
                jobset = fxmlLoader.load();
                viewJobsetController = fxmlLoader.getController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jobset;
    }

    private static Node getCompanyNode() {
        if (companyset == null) {
            var fxmLoader = new FXMLLoader(CandidateDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/candidate/companyset-candidate.fxml"));
            try {
                companyset = fxmLoader.load();
//                viewCompanysetController = fxmLoader.getController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return companyset;
    }

}