package utfpr.cc66c.server.controllers.candidate;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.candidate.ChooseCandidate;
import utfpr.cc66c.server.services.candidate.auth.LoginCandidate;
import utfpr.cc66c.server.validators.AuthValidator;

public class ChooseCandidateController {
    public static String chooseCandidate(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        var candidate_id = fields.get("id_user");
        if (candidate_id == null) {
            return fieldErrorResponse();
        }
        if (!assertUserExists(candidate_id)) {
            return userDontExist();
        }
        var token = fields.get("token");
        var recruiter_id = JWTController.getIdClaim(token);

        ChooseCandidate.chooseCandidate(candidate_id, recruiter_id);
        return successResponse();
    }

    private static String successResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "CHOOSE_CANDIDATE");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String fieldErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "CHOOSE_CANDIDATE");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String userDontExist() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "CHOOSE_CANDIDATE");
        json.put("status", "CANDIDATE_NOT_FOUND");
        json.set("data", data);

        return json.toString();
    }

    private static boolean assertUserExists(String id) {
        return LoginCandidate.getCandidateEmailByCandidateId(id) != null;
    }
}
