package utfpr.cc66c.server.controllers.recruiter.profile;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.services.recruiter.profile.DeleteRecruiter;
import utfpr.cc66c.server.validators.AuthValidator;

public class DeleteRecruiterController {
    public static String deleteRecruiter(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }

        var token = fields.get("token");
        var id = JWTController.getIdClaim(token);
        ServerController.removeSession(token);

        DeleteRecruiter.deleteRecruiter(id);

        var response = JsonNodeFactory.instance.objectNode();
        response.put("operation", "DELETE_ACCOUNT_RECRUITER");
        response.put("status", "SUCCESS");
        response.set("data", JsonNodeFactory.instance.objectNode());

        return response.toString();
    }
}

