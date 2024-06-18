package utfpr.cc66c.client.controllers.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.services.AuthRequestHandler;
import utfpr.cc66c.core.serializers.JsonFields;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileRecruiterViewController implements Initializable {
    public static final ObjectMapper mapper = new ObjectMapper();
    public TextField emailTextField;
    public TextField passwordTextField;
    public TextField nameTextField;
    public Button saveButton;
    public Button deleteButton;
    public TextField industryTextField;
    public TextArea descriptionTextArea;
    public TextField tokenField;

    public void loadRecruiterInfo(String response) {
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
        industryTextField.setText(fields.get("industry"));
        descriptionTextArea.setText(fields.get("description"));
    }

    public void saveButtonAction() {
        var json = mapper.createObjectNode();
        json.put("operation", "UPDATE_ACCOUNT_RECRUITER");
        json.put("token", SessionController.getToken());

        var data = mapper.createObjectNode();
        var email = emailTextField.getText();
        var password = passwordTextField.getText();
        var name = nameTextField.getText();
        var description = descriptionTextArea.getText();
        var industry = industryTextField.getText();
        if (email.isBlank() || password.isBlank() || name.isBlank() || description.isBlank() || industry.isBlank()) {
            System.out.println("[DEBUG] Invalid field");
            return;
        }
        data.put("email", email);
        data.put("password", password);
        data.put("name", name);
        data.put("industry", industry);
        data.put("description", description);

        json.set("data", data);
        AuthRequestHandler.sendUpdateRequest(json.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var lookupRecruiter = AuthRequestHandler.sendLookupRequest();
        System.out.println("[INFO] Lookup response: " + lookupRecruiter);
        loadRecruiterInfo(lookupRecruiter);
    }

    public void deleteButtonAction() {
        var json = mapper.createObjectNode();
        json.put("operation", "DELETE_ACCOUNT_RECRUITER");
        json.put("token", SessionController.getToken());

        json.set("data", mapper.createObjectNode());
        var response = ClientConnectionController.requestResponse(json.toString());
        System.out.println("[INFO] Delete response: " + response);

        ApplicationViewController.logout();
    }

    public void testButtonAction(ActionEvent actionEvent) {
        var request = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.put("skill", "C");
        data.put("experience", "10");
        data.put("id", "45");
        data.put("filter", "E");

        request.set("data", data);
        request.put("token", tokenField.getText());

        request.put("operation", "INCLUDE_JOB");
        System.out.println(ClientConnectionController.requestResponse(request.toString()));

        request.put("operation", "LOOKUP_JOB");
        System.out.println(ClientConnectionController.requestResponse(request.toString()));

        request.put("operation", "LOOKUP_JOBSET");
        System.out.println(ClientConnectionController.requestResponse(request.toString()));

        request.put("operation", "DELETE_JOB");
        System.out.println(ClientConnectionController.requestResponse(request.toString()));

        request.put("operation", "UPDATE_JOB");
        System.out.println(ClientConnectionController.requestResponse(request.toString()));

        request.put("operation", "SEARCH_JOB");
        System.out.println(ClientConnectionController.requestResponse(request.toString()));
    }
}
