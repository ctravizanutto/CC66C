package utfpr.cc66c.client.controllers.views.candidate;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.ToggleSwitch;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.services.LogoutHandler;

public class SidebarCandidateViewController {
    public AnchorPane profileButton;
    public Button logoutButton;
    public ToggleSwitch toggleAnd;

    public void logoutAction() {
        LogoutHandler.sendLogoutRequest();
    }

    public void onSkillsetButtonClick() {
        ApplicationViewController.toCandidateSkillset();
    }

    public void onProfileClick() {
        ApplicationViewController.toCandidateProfile();
    }

    public void onJobsButtonClick() {
        ApplicationViewController.toCandidateJobset(toggleAnd.isSelected());
    }
}
