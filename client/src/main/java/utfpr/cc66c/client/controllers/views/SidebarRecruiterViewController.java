package utfpr.cc66c.client.controllers.views;

import javafx.scene.layout.AnchorPane;
import utfpr.cc66c.client.services.LogoutHandler;

public class SidebarRecruiterViewController {
    public AnchorPane profileButton;

    public void onCandidatesButtonAction() {
    }

    public void onJobsButtonAction() {
    }

    public void onProfileButtonClicked() {
    }

    public void onLogoutAction() {
        LogoutHandler.sendLogoutRequest();
    }
}
