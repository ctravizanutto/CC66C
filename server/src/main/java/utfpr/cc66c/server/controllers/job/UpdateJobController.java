package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.core.validators.SkillDataset;
import utfpr.cc66c.server.services.jobs.LookupJob;
import utfpr.cc66c.server.services.jobs.UpdateJob;
import utfpr.cc66c.server.validators.AuthValidator;

import java.util.Arrays;
import java.util.Map;

public class UpdateJobController {
    public static String updateJob(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        if (assertFields(fields)) {
            return invalidField();
        }
        var token = fields.get("token");
        var recruiter_id = JWTController.getIdClaim(token);
        var skill = fields.get("skill");
        var experience = fields.get("experience");
        var job_id = fields.get("id");
        if (!Arrays.asList(SkillDataset.dataset).contains(skill)) {
            return invalidField();
        }
        if (LookupJob.lookupJob(recruiter_id, job_id) == null) {
            return notFound();
        }
        UpdateJob.updateJob(recruiter_id, skill, experience, job_id);
        return success();
    }

    private static boolean assertFields(Map<String, String> fields) {
        var skill = fields.get("skill");
        var experience = fields.get("experience");
        var id = fields.get("id");

        if (skill != null && experience != null && id != null) {
            return !skill.isEmpty() && !experience.isEmpty() && !id.isEmpty();
        }
        return false;
    }

    private static String invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_JOB");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String notFound() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_JOB");
        json.put("status", "JOB_NOT_FOUND");
        json.set("data", data);

        return json.toString();
    }

    private static String success() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "UPDATE_JOB");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }
}
