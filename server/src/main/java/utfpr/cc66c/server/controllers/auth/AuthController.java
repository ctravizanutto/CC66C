package utfpr.cc66c.server.controllers.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;

public class AuthController {
    public static String login(ObjectNode json) {
        var fields = JsonFields.getAllFields(json);
        var status = LoginController.getLoginStatus(fields.get("operation"), fields.get("email"), fields.get("password"));

        json.set("data", JsonNodeFactory.instance.objectNode());
        json.put("status", status);
        return json.toString();
    }

    public static String signup(ObjectNode json) {
        var status = SignupController.getSignupStatus(json);
        
        json.put("status", status);
        json.set("data", JsonNodeFactory.instance.objectNode());
        return json.toString();
    }

}
