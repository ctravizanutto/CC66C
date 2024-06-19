package utfpr.cc66c.client.controllers.views;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.ToggleSwitch;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.services.LogoutHandler;
import utfpr.cc66c.client.services.ParseSkillset;
import utfpr.cc66c.client.services.SkillsetConnectionHandler;

public class SidebarCandidateViewController {
    //    public Button skillsetButton;
//    public Button jobsButton;
    public AnchorPane profileButton;
    public Button logoutButton;
    public ToggleSwitch toogleAnd;

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
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();
        var skillNode = JsonNodeFactory.instance.arrayNode();

        data.put("filter", toogleAnd.isSelected() ? "AND" : "OR");
        data.put("experience", "20");
        int experience = 0;

        var response = SkillsetConnectionHandler.sendSkillsetLookup();
        var skillset = ParseSkillset.parseSkillset(response);
        for (var skill : skillset) {
            if (Integer.parseInt(skill.getExperience()) > experience) {
                experience = Integer.parseInt(skill.getExperience());
            }
            skillNode.add(skill.getName());
        }
        data.set("skill", skillNode);
        json.set("data", data);
        json.put("operation", "SEARCH_JOB");
        json.put("token", SessionController.getToken());

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }
}
