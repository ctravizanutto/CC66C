package utfpr.cc66c.server.controllers.candidate.profile;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.candidate.auth.LoginCandidate;
import utfpr.cc66c.server.services.candidate.profile.UpdateCandidate;
import utfpr.cc66c.server.validators.AuthValidator;

import java.util.Map;
import java.util.Objects;

public class UpdateCandidateController {
    public static String updateCandidate(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        if (!assertRequestFields(fields)) {
            return fieldErrorResponse();
        }
        var email = fields.get("email");
        var token = fields.get("token");
        var id = JWTController.getIdClaim(token);
        if (!assertUserDontExist(email, id)) {
            return loginErrorResponse();
        }

        UpdateCandidate.update(id, fields);
        return successResponse();
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

    private static boolean assertUserDontExist(String email, String id) {
        if (LoginCandidate.getCandidatePasswordByEmail(email) != null) {
            return !Objects.equals(id, LoginCandidate.getCandidatePasswordByEmail(email));
        }
        return true;
    }

    private static String loginErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_ACCOUNT_CANDIDATE");
        json.put("status", "INVALID_LOGIN");
        json.set("data", data);

        return json.toString();
    }

    private static String successResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_ACCOUNT_CANDIDATE");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String fieldErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_ACCOUNT_CANDIDATE");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }
}
