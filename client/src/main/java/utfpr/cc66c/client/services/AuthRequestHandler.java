package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.ClientConnectionController;
import utfpr.cc66c.core.models.LoginModel;
import utfpr.cc66c.core.models.SignupModel;
import utfpr.cc66c.core.serializers.JsonFields;

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
        var test = JsonFields.getStringFields(json);
        System.out.println(test);
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
        var test = JsonFields.getStringFields(json);
        System.out.println(test);
    }

}
