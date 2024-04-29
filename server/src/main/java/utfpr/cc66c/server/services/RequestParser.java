package utfpr.cc66c.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.controllers.auth.AuthController;

public class RequestParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String parseJson(String request) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var operation = json.get("operation").asText();
        switch (operation) {
            // Auth
            case "LOGIN_CANDIDATE", "LOGIN_RECRUITER" -> {
                return AuthController.login(json);
            }
            case "SIGNUP_CANDIDATE", "SIGNUP_RECRUITER" -> {
                return AuthController.signup(json);
            }
            case "LOGOUT_CANDIDATE", "LOGOUT_RECRUITER" -> {
                return AuthController.logout(json);
            }
            // Lookup
            case "LOOKUP_ACCOUNT_CANDIDATE", "LOOKUP_ACCOUNT_RECRUITER" -> {
                return ProfileManager.lookup(json);
            }
            // Delete
            case "DELETE_ACCOUNT_CANDIDATE", "DELETE_ACCOUNT_RECRUITER" -> {
                return ProfileManager.delete(json);
            }
            // Update
            case "UPDATE_ACCOUNT_CANDIDATE", "UPDATE_ACCOUNT_RECRUITER" -> {
                return ProfileManager.update(json);
            }
            default -> {
                System.out.println("[ERROR] Invalid operation: " + operation);
                json.put("status", "INVALID_OPERATION");
                json.set("data", JsonNodeFactory.instance.objectNode());

                return json.toString();
            }
        }
    }

}
