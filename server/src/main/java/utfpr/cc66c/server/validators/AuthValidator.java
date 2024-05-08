package utfpr.cc66c.server.validators;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.server.controllers.ServerController;

public class AuthValidator {
    public static Boolean validateTokenOnRequest(ObjectNode request) {
        var fields = JsonFields.getStringFields(request);
        var token = fields.get("token");
        request.set("data", JsonNodeFactory.instance.objectNode());
        request.remove("token");

        if (!ServerController.checkTokenOnSession(token)) {
            request.put("status", "INVALID_TOKEN");
            return false;
        }
        return true;
    }
}
