package utfpr.cc66c.server.controllers.candidate.profile;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.services.candidate.profile.DeleteCandidate;
import utfpr.cc66c.server.validators.AuthValidator;

public class DeleteCandidateController {
    public static String deleteCandidate(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }

        var token = fields.get("token");
        var id = JWTController.getIdClaim(token);
        ServerController.removeSession(token);

        DeleteCandidate.deleteCandidate(id);

        var response = JsonNodeFactory.instance.objectNode();
        response.put("operation", "DELETE_ACCOUNT_CANDIDATE");
        response.put("status", "SUCCESS");
        response.set("data", JsonNodeFactory.instance.objectNode());

        return response.toString();
    }
}

