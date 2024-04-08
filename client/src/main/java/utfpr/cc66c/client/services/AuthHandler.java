package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.ClientConnectionController;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.serializers.JsonFields;

public class AuthHandler {
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
            throw new RuntimeException("[ERROR] Invalid json email response.");
        }
        var test = JsonFields.getAllFields(json);
        System.out.println(test);
    }
}
