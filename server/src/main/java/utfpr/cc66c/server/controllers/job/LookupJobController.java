package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.jobs.LookupJob;
import utfpr.cc66c.server.validators.AuthValidator;

public class LookupJobController {
    public static String lookupJob(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }

        var token = fields.get("token");
        var recruiter_id = JWTController.getIdClaim(token);
        var job_id = fields.get("id");
        if (job_id == null || job_id.isEmpty()) {
            return notFound();
        }

        var job = LookupJob.lookupJob(recruiter_id, job_id);
        if (job == null) {
            return notFound();
        }
        return success(job_id, job);
    }

    private static String notFound() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "LOOKUP_JOB");
        json.put("status", "JOB_NOT_FOUND");
        json.set("data", data);

        return json.toString();

    }

    private static String success(String job_id, String[] job_data) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "LOOKUP_JOB");
        json.put("status", "SUCCESS");
        data.put("id", job_id);
        data.put("skill", job_data[0]);
        data.put("experience", job_data[1]);
        data.put("available", job_data[2]);
        data.put("search", job_data[3]);
        json.set("data", data);

        return json.toString();
    }
}
