package utfpr.cc66c.server.controllers.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTValidator;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.validators.AuthValidator;

import java.util.Objects;

public class AuthController {
    public static String login(ObjectNode json) {
        var data = JsonNodeFactory.instance.objectNode();
        if (AuthValidator.assertLogin(json)) {
            var fields = JsonFields.getStringFields(json);
            var operation = fields.get("operation");
            var email = fields.get("email");

            var status = LoginController.getLoginStatus(operation, email, fields.get("password"));
            json.put("status", status);

            if (Objects.equals(status, "SUCCESS")) {
                var token = generateToken(operation, email);
                data.put("token", token);
            }
        } else {
            json.put("status", "INVALID_FIELD");
        }

        json.set("data", data);

        return json.toString();
    }

    private static String generateToken(String operation, String email) {
        String id;
        String token;
        if (operation.contains("CANDIDATE")) {
            id = LoginController.getCandidateIdByEmail(email);
            token = JWTValidator.generateToken(id, "CANDIDATE");
        } else {
            id = LoginController.getRecruiterIdByEmail(email);
            token = JWTValidator.generateToken(id, "RECRUITER");
        }

        ServerController.addSession(token);
        return token;
    }

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

    public static String logout(ObjectNode json) {
        if (!validateToken(json)) {
            return json.toString();
        }

        json.put("status", "SUCCESS");
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
