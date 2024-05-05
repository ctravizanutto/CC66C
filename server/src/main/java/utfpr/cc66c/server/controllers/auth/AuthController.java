package utfpr.cc66c.server.controllers.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.validators.AuthValidator;


public class AuthController {

    public static String signup(ObjectNode json) {
        if (AuthValidator.assertSignUp(json)) {
            var status = SignupController.getSignupStatus(json);
            json.put("status", status);
        } else {
            json.put("status", "INVALID_FIELD");
        }

        json.set("data", JsonNodeFactory.instance.objectNode());
        return json.toString();
    }

    public static Boolean validateToken(ObjectNode json) {
        var fields = JsonFields.getStringFields(json);
        var token = fields.get("token");
        json.set("data", JsonNodeFactory.instance.objectNode());
        json.remove("token");

        if (!ServerController.checkTokenOnSession(token)) {
            json.put("status", "INVALID_TOKEN");
            return false;
        }
        return true;
    }
}
