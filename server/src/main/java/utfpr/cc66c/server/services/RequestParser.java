package utfpr.cc66c.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.controllers.AuthController;

public class RequestParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String parseJSON(String request) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var operation = json.get("operation").asText();
        switch (operation) {
            case "LOGIN_CANDIDATE", "LOGIN_RECRUITER" -> {
                return AuthController.login(json);
            }
            case "SIGNUP_CANDIDATE", "SIGNUP_RECRUITER" -> {
                return "TODO";
            }
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        }
    }

}
