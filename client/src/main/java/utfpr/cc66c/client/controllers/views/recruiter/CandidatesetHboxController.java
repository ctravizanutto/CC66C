package utfpr.cc66c.client.controllers.views.recruiter;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;

public class CandidatesetHboxController {
    @FXML
    private Text candidateName;
    private String candidateId;

    public void chooseOnMouseClicked() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.put("id_user", candidateId);

        var token = SessionController.getToken();
        json.set("data", data);
        json.put("operation", "CHOOSE_CANDIDATE");
        json.put("token", token);

        var response = ClientConnectionController.requestResponse(json.toString());
        System.out.println("[INFO] Response = " + response);
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName.setText(candidateName);
    }
}
