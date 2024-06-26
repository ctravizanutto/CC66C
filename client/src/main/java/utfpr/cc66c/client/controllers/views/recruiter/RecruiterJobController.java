package utfpr.cc66c.client.controllers.views.recruiter;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.services.JobsetConnectionHandler;
import utfpr.cc66c.client.views.RecruiterDashboardFactory;
import utfpr.cc66c.core.validators.SkillDataset;

import java.net.URL;
import java.util.ResourceBundle;

public class RecruiterJobController implements Initializable {
    public TextField experienceTextField;
    public Button deleteButton;
    public ChoiceBox<String> skillChoiceBox;
    public String id;
    public CheckBox availableCheckBox;
    public CheckBox searchableCheckBox;

    public void saveButtonAction() {
        if (deleteButton.isDisable()) {
            JobsetConnectionHandler.
                    sendJobInclude(skillChoiceBox.getValue(), experienceTextField.getText(), availableCheckBox.isSelected(), searchableCheckBox.isSelected());
        } else {
            JobsetConnectionHandler.sendJobUpdate(id, skillChoiceBox.getValue(), experienceTextField.getText());
        }
        RecruiterDashboardFactory.updateJobsetList();
        ApplicationViewController.toRecruiterJobset();
    }

    public void deleteButtonAction() {
        JobsetConnectionHandler.sendJobDelete(id);
        RecruiterDashboardFactory.updateJobsetList();
        ApplicationViewController.toRecruiterJobset();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var skillDataset = FXCollections.observableArrayList(SkillDataset.dataset);
        skillChoiceBox.setItems(skillDataset);
    }

    public void setAvailable() {
        JobsetConnectionHandler.sendJobAvailable(id, availableCheckBox.isSelected());
    }

    public void setSearchable() {
        JobsetConnectionHandler.sendJobSearchable(id, searchableCheckBox.isSelected());
    }
}
