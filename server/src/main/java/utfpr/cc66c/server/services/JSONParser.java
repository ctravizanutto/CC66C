package utfpr.cc66c.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Objects;

public class JSONParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String parseJSON(String request) {
        ObjectNode json = null;
        try {
            json = (ObjectNode) mapper.readTree(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var operation = json.get("operation").asText();

        if (Objects.equals(operation, "LOGIN_CANDIDATE")) {
            json.set("data", mapper.createObjectNode());
            json.put("status", "SUCCESS");
            return json.toString();
        }
        return "";
    }

}
