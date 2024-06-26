package utfpr.cc66c.client.controllers.views.candidate;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;

import java.net.URL;
import java.util.ResourceBundle;

public class SkillsetCandidateHboxController implements Initializable {
    public Text skillText;
    public Text expText;

    public void onMouseClicked() {
        ApplicationViewController.toCandidateEditSkill(expText.getText(), skillText.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        skillText.setText("NOT_LOADED");
        expText.setText("NOT_LOADED");
    }

    public void setText(String skillset, String experience) {
        skillText.setText(skillset);
        expText.setText(experience);
    }
}
