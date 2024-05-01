package utfpr.cc66c.client.controllers.views;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utfpr.cc66c.client.services.LogoutHandler;

public class SidebarCandidateViewController {
    public Button skillsetButton;
    public Button jobsButton;
    public AnchorPane profileButton;
    public Button logoutButton;

    public void logoutAction() {
        LogoutHandler.sendLogoutRequest();
    }
}
