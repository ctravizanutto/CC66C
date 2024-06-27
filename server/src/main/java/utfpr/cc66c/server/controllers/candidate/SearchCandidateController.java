package utfpr.cc66c.server.controllers.candidate;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.server.services.candidate.SearchCandidate;
import utfpr.cc66c.server.validators.AuthValidator;

public class SearchCandidateController {
    public static String searchCandidate(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        var data = (ObjectNode) request.get("data");
        if (!AuthValidator.validateTokenOnRequest(request)) {
            return request.toString();
        }
//        if (!assertFields(data)) {
//            return invalidField();
//        }
        var experience = fields.get("experience");
        var filter = fields.get("filter");
        var skills = data.get("skill");
        String[] skillset = null;
        if (skills != null) {
            skillset = new String[skills.size()];
            for (int i = 0; i < skills.size(); i++) {
                skillset[i] = skills.get(i).asText();
            }
        }
        data = SearchCandidate.searchCandidate(skillset, experience, filter);
        return success(data);
    }

    private static boolean assertFields(ObjectNode request) {
        var skill = request.get("skill");
        var experience = request.get("experience");

        return (skill != null && skill.isEmpty()) || (experience != null && experience.isEmpty());
    }

    private static String invalidField() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.put("operation", "SEARCH_CANDIDATE");
        json.put("status", "INVALID_FIELD");
        json.set("data", data);

        return json.toString();
    }

    private static String success(ObjectNode data) {
        var json = JsonNodeFactory.instance.objectNode();
        json.set("data", data);
        json.put("operation", "SEARCH_CANDIDATE");
        json.put("status", "SUCCESS");

        return json.toString();
    }
}
