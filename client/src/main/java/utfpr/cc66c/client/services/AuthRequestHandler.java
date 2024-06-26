package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.controllers.views.ApplicationViewController;
import utfpr.cc66c.client.controllers.views.LoginViewController;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.models.SignupModel;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.types.UserType;

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
            System.out.printf("[INFO] Login response: %s\n", json);
        } catch (JsonProcessingException e) {
            System.out.println("[ERROR] Invalid json login response.");
            return;
        }
        var fields = JsonFields.getStringFields(json);
        var token = fields.get("token");
        if (token == null) {
            System.out.println("[ERROR] Invalid token in login response.");
            return;
        }
        SessionController.setToken(token);
        if (Objects.equals(fields.get("status"), "SUCCESS")) {
            if (Objects.equals(fields.get("operation"), "LOGIN_CANDIDATE")) {
                ApplicationViewController.toCandidateDashboard();
            } else {
                ApplicationViewController.toRecruiterDashboard();
            }

        }
    }

    public static void sendSignupRequest(SignupModel model) {
        ObjectNode json = mapper.valueToTree(model);
        json.put("operation", model.getLoginModel().getSingUpOperation());

        var response = ClientConnectionController.requestResponse(json.toString());
        parseSignupResponse(response);
    }

    private static void parseSignupResponse(String response) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(response);
        } catch (JsonProcessingException e) {
            System.out.println("[ERROR] Invalid json signup response.");
            return;
        }
        var fields = JsonFields.getStringFields(json);
        System.out.printf("[INFO] Signup response: %s\n", fields);
    }

    public static String sendLookupRequest() {
        var json = JsonNodeFactory.instance.objectNode();
        json.set("data", JsonNodeFactory.instance.objectNode());
        if (LoginViewController.getInstance().toggleSwitchToUserType() == UserType.CANDIDATE) {
            json.put("operation", "LOOKUP_ACCOUNT_CANDIDATE");
        } else {
            json.put("operation", "LOOKUP_ACCOUNT_RECRUITER");
        }
        json.put("token", SessionController.getToken());

        return ClientConnectionController.requestResponse(json.toString());
    }

    public static void sendUpdateRequest(String request) {
        System.out.println("[INFO] Update response: " + ClientConnectionController.requestResponse(request));
    }
}
