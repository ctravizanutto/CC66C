package utfpr.cc66c.server.controllers.recruiter.profile;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.recruiter.profile.LookupRecruiter;
import utfpr.cc66c.server.validators.AuthValidator;

public class LookupRecruiterController {
    public static String lookupRecruiter(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        var response = JsonNodeFactory.instance.objectNode();
        var responseData = JsonNodeFactory.instance.objectNode();
        response.put("status", "SUCCESS");
        response.set("operation", request.get("operation"));
        response.set("data", responseData);

        var token = fields.get("token");

        var id = JWTController.getIdClaim(token);
        LookupRecruiter.lookupToData(id, responseData);

        return response.toString();
    }
}
