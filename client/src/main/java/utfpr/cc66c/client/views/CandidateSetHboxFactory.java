package utfpr.cc66c.client.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import utfpr.cc66c.client.controllers.views.recruiter.CandidatesetHboxController;

import java.io.IOException;

public class CandidateSetHboxFactory {
    public static HBox createCandidatesetHbox(String name, String id) {
        var loader = new FXMLLoader(CandidateSetHboxFactory.class.getResource("/utfpr/cc66c/client/dashboard/recruiter/hbox-candidateset.fxml"));

        HBox hbox;
        try {
            hbox = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CandidatesetHboxController controller = loader.getController();
        controller.setCandidateId(id);
        controller.setCandidateName(name);

        return hbox;
    }
}
