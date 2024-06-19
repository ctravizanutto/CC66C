package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.core.validators.SkillDataset;
import utfpr.cc66c.server.services.jobs.IncludeJob;
import utfpr.cc66c.server.validators.AuthValidator;

import java.util.Arrays;
import java.util.Map;

public class IncludeJobController {
    public static String includeJob(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        if (!assertFields(fields)) {
            return invalidField();
        }
        var token = fields.get("token");
        var id = JWTController.getIdClaim(token);
        var skill = fields.get("skill");
        if (!Arrays.asList(SkillDataset.dataset).contains(skill)) {
            return invalidSKill();
        }

        IncludeJob.includeJob(id, fields);
        return success();
    }

    private static boolean assertFields(Map<String, String> fields) {
        var skill = fields.get("skill");
        var experience = fields.get("experience");

        if (skill != null && experience != null) {
            return !skill.isEmpty() && !experience.isEmpty();
        }
        return false;
    }

    private static String success() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "INCLUDE_JOB");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "INCLUDE_JOB");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String invalidSKill() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "INCLUDE_JOB");
        json.put("status", "SKILL_NOT_EXISTS");
        json.set("data", data);

        return json.toString();
    }
}
