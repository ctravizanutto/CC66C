package utfpr.cc66c.server.controllers.recruiter.auth;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.server.services.recruiter.auth.LoginRecruiter;
import utfpr.cc66c.server.services.recruiter.auth.SignupRecruiter;

import java.util.Map;

public class SignupRecruiterController {
    public static String signupRecruiter(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!assertRequestFields(fields)) {
            return fieldErrorResponse();
        }

        var email = fields.get("email");
        var password = fields.get("password");
        var name = fields.get("name");
        var description = fields.get("description");
        var industry = fields.get("industry");
        if (!assertUserDontExist(email)) {
            return userExistsErrorResponse();
        }

        SignupRecruiter.signup(email, password, name, description, industry);
        return successResponse();
    }

    private static String successResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SIGNUP_RECRUITER");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String fieldErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SIGNUP_RECRUITER");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String userExistsErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SIGNUP_RECRUITER");
        json.put("status", "USER_EXISTS");
        json.set("data", data);

        return json.toString();
    }

    private static boolean assertUserDontExist(String email) {
        return LoginRecruiter.getRecruiterPasswordByEmail(email) == null;
    }

    private static boolean assertRequestFields(Map<String, String> fields) {
        var email = fields.get("email");
        var password = fields.get("password");
        var name = fields.get("name");
        var description = fields.get("description");
        var industry = fields.get("industry");

        if (email != null && password != null && name != null && description != null && industry != null) {
            return !email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !description.isEmpty() && !industry.isEmpty();
        }
        return false;
    }
}
