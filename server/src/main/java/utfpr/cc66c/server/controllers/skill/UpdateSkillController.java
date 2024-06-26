package utfpr.cc66c.server.controllers.skill;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.core.validators.SkillDataset;
import utfpr.cc66c.server.services.skill.LookupSkill;
import utfpr.cc66c.server.services.skill.UpdateSkill;
import utfpr.cc66c.server.validators.AuthValidator;

import java.util.Arrays;
import java.util.Map;

public class UpdateSkillController {
    public static String updateSkill(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        if (!assertRequestFields(fields)) {
            return fieldErrorResponse();
        }
        var token = fields.get("token");
        var skill = fields.get("skill");
        var id = JWTController.getIdClaim(token);
        if (!Arrays.asList(SkillDataset.dataset).contains(skill)) {
            return skillInvalidErrorResponse();
        }
        var experience = LookupSkill.lookupSkill(id, skill);
        if (experience != null) {
            return skillInvalidErrorResponse();
        }
        var newSkill = fields.get("newSkill");
        UpdateSkill.updateSkill(id, skill, newSkill);
        return successResponse();
    }

    private static boolean assertRequestFields(Map<String, String> fields) {
        var skill = fields.get("skill");
        var newSkill = fields.get("newSkill");
        var experience = fields.get("experience");

        if (skill != null && newSkill != null && experience != null) {
            return !skill.isEmpty() && !newSkill.isEmpty() && !experience.isEmpty();
        }
        return false;
    }

    private static String fieldErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_SKILL");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String skillInvalidErrorResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_SKILL");
        json.put("status", "SKILL_NOT_FOUND");
        json.set("data", data);

        return json.toString();
    }

    private static String successResponse() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_SKILL");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }
}
