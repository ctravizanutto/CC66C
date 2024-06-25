package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.jobs.JobAvailable;
import utfpr.cc66c.server.services.jobs.LookupJob;
import utfpr.cc66c.server.validators.AuthValidator;

import java.util.Map;

public class JobAvailableController {
    public static String setJobAvailable(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        if (!assertFields(fields)) {
            return invalidField();
        }
        var job_id = fields.get("id");
        var available = fields.get("available");
        var token = fields.get("token");
        var recruiter_id = JWTController.getIdClaim(token);
        var job = LookupJob.lookupJob(recruiter_id, job_id);
        if (job == null) {
            return notFound();
        }
        JobAvailable.setJobAvailable(job_id, recruiter_id, available);
        return success();

    }

    private static boolean assertFields(Map<String, String> fields) {
        var id = fields.get("id");
        var available = fields.get("available");

        if (id != null && available != null) {
            return !id.isEmpty() && !available.isEmpty();
        }
        return false;
    }

    private static String invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SET_JOB_AVAILABLE");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String success() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SET_JOB_AVAILABLE");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String notFound() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SET_JOB_AVAILABLE");
        json.put("status", "JOB_NOT_FOUND");
        json.set("data", data);

        return json.toString();

    }
}
