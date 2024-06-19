package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import utfpr.cc66c.client.controllers.views.SkillsetCandidateHboxController;

import java.io.IOException;

public class CandidateSkillHboxFactory {
    public static HBox createCandidateSkillHbox(String skill, String experience, int order) {
        var loader = new FXMLLoader(CandidateSkillHboxFactory.class.getResource("/utfpr/cc66c/client/dashboard/client/hbox-skillset.fxml"));

        HBox hbox;
        try {
            hbox = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SkillsetCandidateHboxController controller = loader.getController();
        controller.setText(skill, experience);
        var color = (order % 2 == 0) ? "#76adea" : "#7076fc";
        hbox.setStyle("-fx-background-color: " + color + ";");

        return hbox;
    }
}
