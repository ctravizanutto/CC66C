package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.server.services.jobs.SearchJob;

import java.util.Map;

public class SearchJobController {
    public static String searchJob(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
//        if (!AuthValidator.validateTokenOnRequest(request)) {
//            return request.toString();
//        }
        if (assertFields(fields)) {
            return invalidField();
        }
        var experience = fields.get("experience");
        var filter = fields.get("filter");
        var skills = request.get("data").get("skill");
        String[] skillset = new String[skills.size()];
        for (int i = 0; i < skills.size(); i++) {
            skillset[i] = skills.get(i).asText();
        }


        var data = SearchJob.searchJob(skillset, experience, filter);
        return success(data);
    }

    private static boolean assertFields(Map<String, String> fields) {
        var skill = fields.get("skill");
        var experience = fields.get("experience");
        if (skill != null) {
            return !skill.isEmpty();
        } else if (experience != null) {
            return !experience.isEmpty();
        }
        return false;
    }

    private static String invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SEARCH_JOB");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String success(ObjectNode data) {
        var json = JsonNodeFactory.instance.objectNode();
        json.set("data", data);
        json.put("operation", "SEARCH_JOB");
        json.put("status", "SUCCESS");

        return json.toString();
    }
}
