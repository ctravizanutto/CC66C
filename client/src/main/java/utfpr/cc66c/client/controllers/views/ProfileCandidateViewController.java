package utfpr.cc66c.client.controllers.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.services.AuthRequestHandler;
import utfpr.cc66c.core.serializers.JsonFields;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileCandidateViewController implements Initializable {
    public static final ObjectMapper mapper = new ObjectMapper();
    public TextField emailTextField;
    public TextField passwordTextField;
    public TextField nameTextField;
    public Button saveButton;
    public Button deleteButton;

    public void loadCandidateInfo(String response) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("[ERROR] Invalid json signup response.");
        }
        var fields = JsonFields.getStringFields(json);
        emailTextField.setText(fields.get("email"));
        passwordTextField.setText(fields.get("password"));
        nameTextField.setText(fields.get("name"));
    }

    public void saveButtonAction() {
        var json = mapper.createObjectNode();
        json.put("operation", "UPDATE_ACCOUNT_CANDIDATE");
        json.put("token", SessionController.getToken());

        var data = mapper.createObjectNode();
        var email = emailTextField.getText();
        var password = passwordTextField.getText();
        var name = nameTextField.getText();
        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            System.out.println("[DEBUG] Invalid field");
            return;
        }
        data.put("email", emailTextField.getText());
        data.put("password", passwordTextField.getText());
        data.put("name", nameTextField.getText());

        json.set("data", data);
        AuthRequestHandler.sendUpdateCandidateRequest(json.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var lookupCandidate = AuthRequestHandler.sendLookupCandidateRequest();
        System.out.println("[INFO] Lookup response: " + lookupCandidate);
        loadCandidateInfo(lookupCandidate);
    }

    public void deleteButtonAction() {
        var json = mapper.createObjectNode();
        json.put("operation", "DELETE_ACCOUNT_CANDIDATE");
        json.put("token", SessionController.getToken());

        json.set("data", mapper.createObjectNode());
        var response = ClientConnectionController.requestResponse(json.toString());
        System.out.println(response);

        ClientConnectionController.shutdown();
        ApplicationViewController.logout();
    }
}
