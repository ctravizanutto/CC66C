package utfpr.cc66c.client.controllers.views;

import javafx.stage.Stage;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.views.CandidateDashboardFactory;
import utfpr.cc66c.client.views.IpViewFactory;
import utfpr.cc66c.client.views.LoginViewFactory;
import utfpr.cc66c.client.views.RecruiterDashboardFactory;

public class ApplicationViewController {
    private static Stage stage;

    public ApplicationViewController(Stage stage) {
        ApplicationViewController.stage = stage;

        stage.setTitle("Client");
        stage.setResizable(false);
        stage.show();

        // display ip scene
        stage.setScene(IpViewFactory.getScene());
    }

    public static void toLogin(String addr) {
        ClientConnectionController.start(addr);
        var loginScene = LoginViewFactory.getInstance().getScene();
        stage.setScene(loginScene);
    }

    public static void logout() {
        var loginScene = LoginViewFactory.getInstance().getScene();
        stage.setScene(loginScene);
    }

    public static void toCandidateDashboard() {
        var scene = CandidateDashboardFactory.getCompanysetScene();
        stage.setScene(scene);
    }

    public static void toCandidateProfile() {
        stage.setScene(CandidateDashboardFactory.getProfileScene());
    }

    public static void toCandidateJobset(boolean andFilter) {
        stage.setScene(CandidateDashboardFactory.getJobsetScene());
        CandidateDashboardFactory.updateJobsetView(andFilter);
    }

    public static void toCandidateSkillset() {
        stage.setScene(CandidateDashboardFactory.getSkillsetScene());
    }

    public static void toCandidateAddSkill() {
        var scene = CandidateDashboardFactory.getSkillAddScene();
        stage.setScene(scene);
    }

    public static void toCandidateEditSkill(String experience, String skill) {
        var scene = CandidateDashboardFactory.getSkillEditScene(experience, skill);
        stage.setScene(scene);
    }

    public static void toRecruiterEditJob(String skill, String experience, String id, boolean available, boolean searchable) {
        stage.setScene(RecruiterDashboardFactory.getJobEditScene(skill, experience, id, available, searchable));
    }

    public static void toRecruiterAddJob() {
        stage.setScene(RecruiterDashboardFactory.getJobAddScene());
    }

    public static void toRecruiterJobset() {
        stage.setScene(RecruiterDashboardFactory.getJobsetScene());
    }

    public static void toRecruiterCandidateSet(boolean andFilter) {
        stage.setScene(RecruiterDashboardFactory.getCandidatesetScene());
        RecruiterDashboardFactory.updateList(andFilter);
    }

    public static void toRecruiterDashboard() {
        stage.setScene(RecruiterDashboardFactory.getBlankScene());
    }

    public static void toRecruiterProfile() {
        stage.setScene(RecruiterDashboardFactory.getProfileScene());
    }

    public void shutdown() {
        ClientConnectionController.shutdown();
    }

}

