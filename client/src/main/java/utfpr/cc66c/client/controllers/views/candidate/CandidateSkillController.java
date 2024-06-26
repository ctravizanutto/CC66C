package utfpr.cc66c.client.controllers.views.candidate;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.services.SkillsetConnectionHandler;
import utfpr.cc66c.client.views.CandidateDashboardFactory;
import utfpr.cc66c.core.validators.SkillDataset;

import java.net.URL;
import java.util.ResourceBundle;

public class CandidateSkillController implements Initializable {
    public TextField experienceTextField;
    public Button deleteButton;
    public ChoiceBox<String> skillChoiceBox;
    public String oldSkill;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var skillDataset = FXCollections.observableArrayList(SkillDataset.dataset);
        skillChoiceBox.setItems(skillDataset);
    }

    public void deleteButtonAction() {
        SkillsetConnectionHandler.sendSkillDelete(skillChoiceBox.getValue());
        CandidateDashboardFactory.updateSkillsetView();
    }

    public void saveButtonAction() {
        if (deleteButton.isDisable()) {
            SkillsetConnectionHandler.sendSkillInclude(skillChoiceBox.getValue(), experienceTextField.getText());
        } else {
            SkillsetConnectionHandler.sendUpdateSkill(oldSkill, skillChoiceBox.getValue(), experienceTextField.getText());
        }
        CandidateDashboardFactory.updateSkillsetView();
    }
}
