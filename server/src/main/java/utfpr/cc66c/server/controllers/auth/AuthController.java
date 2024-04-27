package utfpr.cc66c.server.controllers.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTValidator;
import utfpr.cc66c.server.controllers.ServerController;

import java.util.Objects;

public class AuthController {
    public static String login(ObjectNode json) {
        var fields = JsonFields.getStringFields(json);
        var operation = fields.get("operation");
        var email = fields.get("email");

        var status = LoginController.getLoginStatus(operation, email, fields.get("password"));
        json.put("status", status);

        if (Objects.equals(status, "SUCCESS")) {
            String id;
            String token;
            if (fields.get("operation").contains("CANDIDATE")) {
                id = LoginController.getCandidateIdByEmail(fields.get("email"));
                token = JWTValidator.generateToken(id, "CANDIDATE");
            } else {
                id = LoginController.getRecruiterIdByEmail(fields.get("email"));
                token = JWTValidator.generateToken(id, "RECRUITER");
            }
            ServerController.addSession(token);
            json.put("token", token);
        }

        var data = JsonNodeFactory.instance.objectNode();
        json.set("data", data);

        return json.toString();
    }

    public static String signup(ObjectNode json) {
        var status = SignupController.getSignupStatus(json);
        
        json.put("status", status);
        json.set("data", JsonNodeFactory.instance.objectNode());
        return json.toString();
    }

}
