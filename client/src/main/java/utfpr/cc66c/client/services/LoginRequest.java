package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.ConnectionController;
import utfpr.cc66c.client.controllers.gui.ClientApplicationController;
import utfpr.cc66c.client.types.UserType;

import java.util.Objects;

public class LoginRequest {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendLoginRequest(String emailAddr, String password) {
        sendLoginJSON(emailAddr, password);
    }

    private static void sendLoginJSON(String emailAddr, String password) {
        var json = mapper.createObjectNode();
        var data = mapper.createObjectNode();

        data.put("email", emailAddr);
        data.put("password", password);

        var option = ClientApplicationController.getLoginViewController().getOption();

        json.put("operation", option == UserType.CANDIDATE ? "LOGIN_CANDIDATE" : "LOGIN_RECRUITER");
        json.set("data", data);

        var response = ConnectionController.sendJSON(json.toString());
        parseLoginResponse(response);
    }

    private static void parseLoginResponse(String response) {
        ObjectNode json = null;
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
