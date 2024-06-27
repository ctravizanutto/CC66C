package utfpr.cc66c.client.controllers.views.recruiter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.layout.VBox;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.client.services.ParseJobset;
import utfpr.cc66c.client.views.CandidateSetHboxFactory;
import utfpr.cc66c.core.serializers.JsonFields;

public class CandidatesetRecruiterViewController {
    public VBox vbox;

    public void updateList(boolean andFilter) {
        vbox.getChildren().clear();
        // long ass function really dont want to think bout it though

        // lookup jobset
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.set("data", data);
        json.put("token", SessionController.getToken());
        json.put("operation", "LOOKUP_JOBSET");

        var response = ClientConnectionController.requestResponse(json.toString());
        System.out.println("[INFO] Response " + response);

        var recruiterJobset = ParseJobset.parseJobsetRecruiter(response);
        ArrayNode skillset = JsonNodeFactory.instance.arrayNode();
        int experience = 0;
        for (var job : recruiterJobset) {
            skillset.add(job.skill());
            if (Integer.parseInt(job.experience()) > experience) {
                experience = Integer.parseInt(job.experience());
            }
        }

        // get candidateset
        // parse it
        // add to list with button
        data.set("skill", skillset);
        data.put("experience", String.valueOf(experience));
        data.put("filter", andFilter ? "AND" : "OR");

        json.set("data", data);
        json.put("operation", "SEARCH_CANDIDATE");

        response = ClientConnectionController.requestResponse(json.toString());
        System.out.println("[INFO] Response " + response);

        var mapper = new ObjectMapper();
        try {
            json = (ObjectNode) mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var fields = JsonFields.getStringFields(json);
        data = (ObjectNode) json.get("data");

        if (Integer.parseInt(fields.get("profile_size")) > 0) {
            var candidate_set = (ArrayNode) data.get("profile");
            for (var candidate : candidate_set) {
                var hbox = CandidateSetHboxFactory.createCandidatesetHbox(candidate.get("name").asText(), candidate.get("id_user").asText());
                vbox.getChildren().add(hbox);
            }
        }
    }
}
