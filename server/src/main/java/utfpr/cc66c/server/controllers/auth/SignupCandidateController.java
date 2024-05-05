package utfpr.cc66c.server.controllers.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.server.services.LoginCandidate;
import utfpr.cc66c.server.services.SignupCandidate;

import java.util.Map;

public class SignupCandidateController {
    public static String signupCandidate(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!assertRequestFields(fields)) {
            return fieldErrorResponse();
        }

        var email = fields.get("email");
        var password = fields.get("password");
        var name = fields.get("name");
        if (!assertUserDontExist(email)) {
            return userExistsErrorResponse();
        }

        SignupCandidate.signup(email, password, name);
        return successResponse();
    }

    private static String successResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SIGNUP_CANDIDATE");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String fieldErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SIGNUP_CANDIDATE");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String userExistsErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SIGNUP_CANDIDATE");
        json.put("status", "USER_EXISTS");
        json.set("data", data);

        return json.toString();
    }

    private static boolean assertUserDontExist(String email) {
        return LoginCandidate.getCandidatePasswordByEmail(email) == null;
    }

    private static boolean assertRequestFields(Map<String, String> fields) {
        var email = fields.get("email");
        var password = fields.get("password");
        var name = fields.get("name");

        if (email != null && password != null && name != null) {
            return !email.isEmpty() && !password.isEmpty() && !name.isEmpty();
        }
        return false;
    }
}
