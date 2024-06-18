package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class LookupJobController {
    public static String lookupJob(ObjectNode request) {
        request.put("status", "SUCCESS");
        return request.toString();
    }
}
