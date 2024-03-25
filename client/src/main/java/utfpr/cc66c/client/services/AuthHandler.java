package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.ConnectionController;
import utfpr.cc66c.core.models.LoginModel;

import java.util.Objects;

public class AuthHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendLoginRequest(LoginModel model) {
        sendLoginJSON(model);
    }

    private static void sendLoginJSON(LoginModel model) {
        String json;
        try {
            json = mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var response = ConnectionController.sendJSON(json);
        parseLoginResponse(response);
    }

    private static void parseLoginResponse(String response) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var status = json.get("status").asText();
        if (Objects.equals(status, "SUCCESS")) {
            System.out.println("[INFO] Successful login.");
        }
    }
}
