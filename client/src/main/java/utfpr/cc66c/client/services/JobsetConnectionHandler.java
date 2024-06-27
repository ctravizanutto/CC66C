package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.core.serializers.JsonFields;

public class JobsetConnectionHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String sendJobsetLookup() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.set("data", data);
        json.put("token", SessionController.getToken());
        json.put("operation", "LOOKUP_JOBSET");

        return ClientConnectionController.requestResponse(json.toString());
    }

    public static int parseJobsetLookup(String response) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(response);
            System.out.printf("[INFO] Skillset response: %s\n", json);
        } catch (JsonProcessingException e) {
            System.out.println("[ERROR] Invalid json Skillset response.");
            throw new RuntimeException(e);
        }
        var fields = JsonFields.getStringFields(json);
        return Integer.parseInt(fields.get("jobset_size"));
    }

    public static void sendJobInclude(String skill, String experience, boolean available, boolean searchable) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.put("skill", skill);
        data.put("experience", experience);
        data.put("available", available ? "YES" : "NO");
        data.put("searchable", searchable ? "YES" : "NO");

        json.set("data", data);
        json.put("operation", "INCLUDE_JOB");
        json.put("token", SessionController.getToken());

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }

    public static void sendJobUpdate(String id, String skill, String experience) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.put("id", id);
        data.put("skill", skill);
        data.put("experience", experience);

        json.set("data", data);
        json.put("operation", "UPDATE_JOB");
        json.put("token", SessionController.getToken());

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }

    public static void sendJobDelete(String id) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();
        data.put("id", id);
        json.set("data", data);
        json.put("operation", "DELETE_JOB");
        json.put("token", SessionController.getToken());
        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }

    public static void sendJobAvailable(String id, boolean available) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();
        data.put("id", id);
        data.put("available", available ? "YES" : "NO");

        json.put("token", SessionController.getToken());
        json.put("operation", "SET_JOB_AVAILABLE");
        json.set("data", data);

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }

    public static void sendJobSearchable(String id, boolean searchable) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();
        data.put("id", id);
        data.put("searchable", searchable ? "YES" : "NO");

        json.put("token", SessionController.getToken());
        json.put("operation", "SET_JOB_SEARCHABLE");
        json.set("data", data);

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }

    public static String sendJobSearch(boolean andFilter) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();
        var skillNode = JsonNodeFactory.instance.arrayNode();

        data.put("filter", andFilter ? "AND" : "OR");
//        data.put("experience", "20");
        int experience = 0;

        var response = SkillsetConnectionHandler.sendSkillsetLookup();
        var skillset = ParseSkillset.parseSkillset(response);
        for (var skill : skillset) {
            if (Integer.parseInt(skill.getExperience()) > experience) {
                experience = Integer.parseInt(skill.getExperience());
            }
            skillNode.add(skill.getName());
        }
        data.set("skill", skillNode);
        data.put("experience", experience);
        json.set("data", data);
        json.put("operation", "SEARCH_JOB");
        json.put("token", SessionController.getToken());

        var jobSearch = ClientConnectionController.requestResponse(json.toString());
        System.out.println(jobSearch);
        return jobSearch;
    }
}
