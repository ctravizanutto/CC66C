package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import utfpr.cc66c.client.controllers.views.DashboardController;
import utfpr.cc66c.client.controllers.views.JobsetRecruiterViewController;
import utfpr.cc66c.client.controllers.views.RecruiterJobController;

import java.io.IOException;

public class RecruiterDashboardFactory {
    private static Node sidebar;
    private static Node blank;
    private static Node profile;
    private static Node jobset;
    private static Node job;
    private static JobsetRecruiterViewController jobsetRecruiterViewController;
    private static RecruiterJobController recruiterJobController;

    public static Scene getBlankScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getBlankPane());
        DashboardController.getInstance().setLeft(getRecruiterSidebarNode());
        return scene;
    }

    public static Scene getJobsetScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getRecruiterJobsetNode());
        return scene;
    }

    public static Scene getProfileScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getRecruiterProfileNode());
        return scene;
    }

    public static Scene getJobAddScene() {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getRecruiterJobNode());
        recruiterJobController.deleteButton.setDisable(true);
        recruiterJobController.skillChoiceBox.setValue(null);
        recruiterJobController.experienceTextField.setText(null);
        recruiterJobController.experienceTextField.setDisable(false);
        return scene;
    }

    public static Scene getJobEditScene(String skill, String experience, String id, boolean available, boolean searchable) {
        var scene = DashboardController.getScene();
        DashboardController.getInstance().setRight(getRecruiterJobNode());
        recruiterJobController.deleteButton.setDisable(false);
        recruiterJobController.id = id;
        recruiterJobController.availableCheckBox.setSelected(available);
        recruiterJobController.searchableCheckBox.setSelected(searchable);
        recruiterJobController.skillChoiceBox.setValue(skill);
        recruiterJobController.experienceTextField.setText(experience);
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

    public static void updateJobsetList() {
        jobsetRecruiterViewController.updateList();
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

    private static Node getBlankPane() {
        if (blank == null) {
            var fxmlLoader = new FXMLLoader(RecruiterDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/blank.fxml"));
            try {
                blank = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return blank;
    }

    private static Node getRecruiterJobsetNode() {
        if (jobset == null) {
            var fxmlLoader = new FXMLLoader(RecruiterDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/recruiter/jobset-recruiter.fxml"));
            try {
                jobset = fxmlLoader.load();
                jobsetRecruiterViewController = fxmlLoader.getController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jobset;
    }

    private static Node getRecruiterJobNode() {
        if (job == null) {
            var fxmlLoader = new FXMLLoader(RecruiterDashboardFactory.class.getResource("/utfpr/cc66c/client/dashboard/recruiter/job-recruiter.fxml"));
            try {
                job = fxmlLoader.load();
                recruiterJobController = fxmlLoader.getController();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return job;
    }
}
