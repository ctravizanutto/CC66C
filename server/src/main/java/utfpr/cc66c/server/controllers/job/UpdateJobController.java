package utfpr.cc66c.server.controllers.job;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class UpdateJobController {
    public static String updateJob(ObjectNode request) {
        request.put("status", "SUCCESS");
        return request.toString();
    }
}
