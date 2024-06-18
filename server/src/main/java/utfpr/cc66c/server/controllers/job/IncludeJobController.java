package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class IncludeJobController {
    public static String includeJob(ObjectNode request) {
        request.put("status", "SUCCESS");
        return request.toString();
    }
}
