package utfpr.cc66c.client.controllers.views.recruiter;

import javafx.scene.layout.AnchorPane;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.services.LogoutHandler;

public class SidebarRecruiterViewController {
    public AnchorPane profileButton;

    public void onCandidatesButtonAction() {
    }

    public void onJobsButtonAction() {
        ApplicationViewController.toRecruiterJobset();
    }

    public void onProfileButtonClicked() {
        ApplicationViewController.toRecruiterProfile();
    }

    public void onLogoutAction() {
        LogoutHandler.sendLogoutRequest();
    }
}
