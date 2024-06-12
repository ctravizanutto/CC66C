package utfpr.cc66c.server.controllers.skill;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.skill.LookupSkillset;
import utfpr.cc66c.server.validators.AuthValidator;

public class LookupSkillsetController {
    public static String lookupSkillset(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }


        var token = fields.get("token");
        var id = JWTController.getIdClaim(token);
        var skillset = LookupSkillset.lookupSkillset(id);

        return successResponse(skillset);
    }

    private static String successResponse(ObjectNode skillset) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.set("skillset", skillset);
        data.put("skillset_size", skillset.size());

        json.put("operation", "LOOKUP_SKILLSET");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }
}
