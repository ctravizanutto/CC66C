package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import utfpr.cc66c.client.controllers.views.recruiter.JobsetRecruiterHboxController;

import java.io.IOException;

public class RecruiterJobHboxFactory {
    public static HBox createRecruiterJobHbox(String id, String skill, String experience, int order, boolean available, boolean searchable) {
        var loader = new FXMLLoader(RecruiterJobHboxFactory.class.getResource("/utfpr/cc66c/client/dashboard/recruiter/hbox-jobset.fxml"));

        HBox hbox;
        try {
            hbox = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JobsetRecruiterHboxController controller = loader.getController();
        controller.setText(skill, experience, id, available, searchable);
        var color = (order % 2 == 0) ? "#76adea" : "#7076fc";
        hbox.setStyle("-fx-background-color: " + color + ";");

        return hbox;
    }
}
