package utfpr.cc66c.server.controllers.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.services.LoginCandidate;

import java.util.Map;

public class LoginCandidateController {
    public static String loginCandidate(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!assertRequestFields(fields)) {
            return buildErrorResponse();
        }

        var email = fields.get("email");
        var requestPassword = fields.get("password");
        var dbPassword = LoginCandidate.getCandidatePasswordByEmail(email);

        if (dbPassword != null) {
            if (dbPassword.equals(requestPassword)) {
                var id = LoginCandidate.getCandidateIdByEmail(email);
                return buildSuccessResponse(id);
            }
        }
        return buildErrorResponse();
    }

    public static String logoutCandidate(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        var token = fields.get("token");
        ServerController.removeSession(token);

        var response = JsonNodeFactory.instance.objectNode();
        response.put("operation", "LOGOUT_CANDIDATE");
        response.put("status", "SUCCESS");
        response.set("data", JsonNodeFactory.instance.objectNode());

        return response.toString();
    }

    private static boolean assertRequestFields(Map<String, String> fields) {
        var email = fields.get("email");
        var password = fields.get("password");

        if (email != null && password != null) {
            return !email.isEmpty() && !password.isEmpty();
        }
        return false;
    }

    private static String buildErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "LOGIN_CANDIDATE");
        json.put("status", "INVALID_LOGIN");
        json.set("data", data);

        return json.toString();
    }

    private static String buildSuccessResponse(String id) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();
        var token = JWTController.generateToken(id, "CANDIDATE");

        ServerController.addSession(token);

        json.put("operation", "LOGIN_CANDIDATE");
        json.put("status", "SUCCESS");
        data.put("token", token);
        json.set("data", data);

        return json.toString();
    }
}
