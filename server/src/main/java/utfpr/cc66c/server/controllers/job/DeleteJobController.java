package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DeleteJobController {
    public static String deleteJob(ObjectNode request) {
        request.put("status", "SUCCESS");
        return request.toString();
    }
}
