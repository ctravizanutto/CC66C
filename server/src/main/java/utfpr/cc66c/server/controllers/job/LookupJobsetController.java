package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.jobs.LookupJobset;
import utfpr.cc66c.server.validators.AuthValidator;

public class LookupJobsetController {
    public static String lookupJobset(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }

        var token = fields.get("token");
        var id = JWTController.getIdClaim(token);
        var skillset = LookupJobset.lookupJobset(id);

        return successResponse(skillset);
    }

    private static String successResponse(ArrayNode skillset) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.set("skillset", skillset);
        data.put("skillset_size", skillset.size());

        json.put("operation", "LOOKUP_JOBSET");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }
}
