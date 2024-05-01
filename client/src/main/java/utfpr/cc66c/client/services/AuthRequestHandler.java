package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.models.SignupModel;
import utfpr.cc66c.core.serializers.JsonFields;

import java.util.Objects;

public class AuthRequestHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendLoginRequest(LoginModel model) {
        ObjectNode json = mapper.valueToTree(model);
        json.put("operation", model.getLoginOperation());

        var response = ClientConnectionController.requestResponse(json.toString());
        parseLoginResponse(response);
    }


    private static void parseLoginResponse(String response) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("[ERROR] Invalid json login response.");
        }
        var fields = JsonFields.getStringFields(json);
        var token = fields.get("token");
        if (token == null) {
            throw new RuntimeException("[ERROR] Invalid token in login response.");
        }
        SessionController.setToken(token);
        System.out.printf("[INFO] Login response: %s\n", fields);
        if (Objects.equals(fields.get("status"), "SUCCESS")) {
            ApplicationViewController.toCandidateDashboard();
        }
    }

    public static void sendSignupRequest(SignupModel model) {
        ObjectNode json = mapper.valueToTree(model);
        json.put("operation", model.getLoginModel().getSingupOperation());

        var response = ClientConnectionController.requestResponse(json.toString());
        parseSignupResponse(response);
    }

    private static void parseSignupResponse(String response) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("[ERROR] Invalid json signup response.");
        }
        var fields = JsonFields.getStringFields(json);
        System.out.printf("[INFO] Signup response: %s\n", fields);
    }

    public static String sendLookupCandidateRequest() {
        var json = JsonNodeFactory.instance.objectNode();
        json.set("data", JsonNodeFactory.instance.objectNode());
        json.put("operation", "LOOKUP_ACCOUNT_CANDIDATE");
        json.put("token", SessionController.getToken());

        return ClientConnectionController.requestResponse(json.toString());
    }

    public static void sendUpdateCandidateRequest(String request) {
        ClientConnectionController.requestResponse(request);
    }
}
