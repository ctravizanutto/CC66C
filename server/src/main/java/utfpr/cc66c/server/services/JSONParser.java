package utfpr.cc66c.server.services;

import org.json.JSONObject;

import java.util.Objects;

public class JSONParser {
    public static String parseJSON(String request) {
        var json = new JSONObject(request);
        var operation = json.get("operation");

        if (Objects.equals(operation, "LOGIN_CANDIDATE")) {
            json.put("data", new JSONObject());
            json.put("status", "SUCCESS");
            return json.toString();
        }
        return "";
    }

}
