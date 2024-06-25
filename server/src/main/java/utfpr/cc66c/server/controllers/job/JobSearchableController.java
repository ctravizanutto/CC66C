package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.jobs.JobSearchable;
import utfpr.cc66c.server.services.jobs.LookupJob;
import utfpr.cc66c.server.validators.AuthValidator;

import java.util.Map;

public class JobSearchableController {
    public static String setJobSearchable(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
        if (!assertFields(fields)) {
            return invalidField();
        }
        var job_id = fields.get("id");
        var searchable = fields.get("searchable");
        var token = fields.get("token");
        var recruiter_id = JWTController.getIdClaim(token);
        var job = LookupJob.lookupJob(recruiter_id, job_id);
        if (job == null) {
            return notFound();
        }
        JobSearchable.setJobSearchable(job_id, recruiter_id, searchable);
        return success();

    }

    private static boolean assertFields(Map<String, String> fields) {
        var id = fields.get("id");
        var searchable = fields.get("searchable");

        if (id != null && searchable != null) {
            return !id.isEmpty() && !searchable.isEmpty();
        }
        return false;
    }

    private static String invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SET_JOB_SEARCHABLE");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String success() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SET_JOB_SEARCHABLE");
        json.put("status", "SUCCESS");
        json.set("data", data);

        return json.toString();
    }

    private static String notFound() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SET_JOB_SEARCHABLE");
        json.put("status", "JOB_NOT_FOUND");
        json.set("data", data);

        return json.toString();

    }
}
