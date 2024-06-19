package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTController;
import utfpr.cc66c.server.services.jobs.DeleteJob;
import utfpr.cc66c.server.services.jobs.LookupJob;
import utfpr.cc66c.server.validators.AuthValidator;

public class DeleteJobController {
    public static String deleteJob(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }

        var token = fields.get("token");
        var recruiter_id = JWTController.getIdClaim(token);
        var job_id = fields.get("id");
        if (job_id == null) {
            return invalidField();
        }
        if (LookupJob.lookupJob(recruiter_id, job_id) == null) {
            return notFound();
        }
        DeleteJob.deleteJob(recruiter_id, job_id);
        return success();
    }

    private static String invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "DELETE_JOB");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String notFound() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "DELETE_JOB");
        json.put("status", "JOB_NOT_FOUND");
        json.set("data", data);

        return json.toString();
    }

    private static String success() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();
        json.put("operation", "DELETE_JOB");
        json.put("status", "SUCCESS");
        json.set("data", data);
        return json.toString();
    }
}
