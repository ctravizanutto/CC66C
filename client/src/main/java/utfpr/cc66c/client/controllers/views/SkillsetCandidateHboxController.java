package utfpr.cc66c.client.controllers.views;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SkillsetCandidateHboxController implements Initializable {
    public Text skillText;
    public Text expText;

    public void onMouseClicked(MouseEvent mouseEvent) {
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
