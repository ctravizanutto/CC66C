package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class LookupJobsetController {
    public static String lookupJobset(ObjectNode request) {
        request.put("status", "SUCCESS");
        return request.toString();
    }
}
