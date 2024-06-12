package utfpr.cc66c.server.controllers.skill;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.skill.DeleteSkill;
import utfpr.cc66c.server.validators.AuthValidator;

public class DeleteSkillController {
    public static String deleteSkill(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }


        var token = fields.get("token");
        var id = JWTController.getIdClaim(token);
        var skill = fields.get("skill");

        if (!DeleteSkill.deleteSkill(id, skill)) {
            return errorResponse();
        }
        return successResponse();
    }

    private static String successResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "DELETE_SKILL");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String errorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "DELETE_SKILL");
        json.put("status", "SKILL_NOT_FOUND");
        json.set("data", data);

        return json.toString();
    }
}
