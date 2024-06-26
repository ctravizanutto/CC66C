package utfpr.cc66c.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.client.controllers.connection.ClientConnectionController;
import utfpr.cc66c.client.controllers.connection.SessionController;
import utfpr.cc66c.core.serializers.JsonFields;

public class SkillsetConnectionHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void sendSkillDelete(String skill) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.put("skill", skill);

        json.set("data", data);
        json.put("token", SessionController.getToken());
        json.put("operation", "DELETE_SKILL");

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }

    public static String sendSkillsetLookup() {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        json.set("data", data);
        json.put("token", SessionController.getToken());
        json.put("operation", "LOOKUP_SKILLSET");

        return ClientConnectionController.requestResponse(json.toString());
    }

    public static int parseSkillsetLookup(String response) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(response);
            System.out.printf("[INFO] Skillset response: %s\n", json);
        } catch (JsonProcessingException e) {
            System.out.println("[ERROR] Invalid json Skillset response.");
            return 0;
        }
        var fields = JsonFields.getStringFields(json);
        return Integer.parseInt(fields.get("skillset_size"));
    }

    public static void sendSkillInclude(String skill, String experience) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.put("skill", skill);
        data.put("experience", experience);

        json.set("data", data);
        json.put("operation", "INCLUDE_SKILL");
        json.put("token", SessionController.getToken());

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }

    public static void sendUpdateSkill(String oldSkill, String newSkill, String experience) {
        var json = JsonNodeFactory.instance.objectNode();
        var data = JsonNodeFactory.instance.objectNode();

        data.put("skill", oldSkill);
        data.put("newSkill", newSkill);
        data.put("experience", experience);

        json.set("data", data);
        json.put("operation", "UPDATE_SKILL");
        json.put("token", SessionController.getToken());

        System.out.println(ClientConnectionController.requestResponse(json.toString()));
    }
}
