package utfpr.cc66c.server.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.DatabaseService;

public class AuthController {
    public static void loginCandidate(ObjectNode json) {
        var email = json.get("data").get("email").toString();
        DatabaseService.getUser(email);

    }
}
