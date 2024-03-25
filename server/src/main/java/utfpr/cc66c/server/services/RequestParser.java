package utfpr.cc66c.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RequestParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String parseJSON(String request) {
        ObjectNode json;
        try {
            json = (ObjectNode) mapper.readTree(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var operation = json.get("operation").asText();
        if (operation.contains("LOGIN")) {
            json.set("data", mapper.createObjectNode());
            json.put("status", "SUCCESS");
            return json.toString();
        } else if (operation.contains("LOGOUT")) {
            return json.toString();
        } else if (operation.contains("SIGN_UP")) {

        }
        return "";
    }

}
