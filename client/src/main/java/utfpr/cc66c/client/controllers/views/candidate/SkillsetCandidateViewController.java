package utfpr.cc66c.client.controllers.views.candidate;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.services.ParseSkillset;
import utfpr.cc66c.client.services.SkillsetConnectionHandler;
import utfpr.cc66c.client.views.CandidateSkillHboxFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SkillsetCandidateViewController implements Initializable {
    public VBox vbox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var response = SkillsetConnectionHandler.sendSkillsetLookup();
        var skillsetSize = SkillsetConnectionHandler.parseSkillsetLookup(response);
        if (skillsetSize > 0) {
            generateSkillsetList(skillsetSize, response);
        }
    }

    public void updateList() {
        vbox.getChildren().subList(1, vbox.getChildren().size()).clear();
        var response = SkillsetConnectionHandler.sendSkillsetLookup();
        var skillsetSize = SkillsetConnectionHandler.parseSkillsetLookup(response);
        if (skillsetSize > 0) {
            generateSkillsetList(skillsetSize, response);
        }
    }

    private void generateSkillsetList(int skillsetSize, String response) {
        var skillset = ParseSkillset.parseSkillset(response);
        for (var skill : skillset) {
            var hbox = CandidateSkillHboxFactory.createCandidateSkillHbox(skill.getName(), skill.getExperience(), skillsetSize--);
            vbox.getChildren().add(hbox);
        }
    }

    public void onAddButtonAction() {
        ApplicationViewController.toCandidateAddSkill();
    }
}
