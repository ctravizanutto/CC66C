package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class SearchJobController {
    public static String searchJob(ObjectNode request) {
        request.put("status", "SUCCESS");
        return request.toString();
    }
}
