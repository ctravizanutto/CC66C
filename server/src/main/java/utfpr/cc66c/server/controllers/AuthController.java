package utfpr.cc66c.server.controllers;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.LoginDatabaseDriver;

import java.util.Objects;

public class AuthController {
    public static String login(ObjectNode json) {
        var operation = json.get("operation").asText();
        var email = json.get("email").asText();
        var password = json.get("password").asText();
        json.set("data", JsonNodeFactory.instance.objectNode());

        String status;
        if (operation.contentEquals("CANDIDATE")) {
            status = loginCandidate(email, password);
        } else {
            status = loginRecruiter(email, password);
        }
        json.put("status", status);
        return json.toString();
    }

    private static String loginCandidate(String email, String password) {
        var correct_password = LoginDatabaseDriver.getCandidatePasswordByEmail(email);
        if (correct_password == null) {
            return "USER_NOT_FOUND";
        }
        if (!Objects.equals(correct_password, password)) {
            return "INVALID_PASSWORD";
        }
        return "SUCCESS";
    }

    private static String loginRecruiter(String email, String password) {
        var correct_password = LoginDatabaseDriver.getRecruiterPasswordByEmail(email);
        if (correct_password == null) {
            return "USER_NOT_FOUND";
        }
        if (!Objects.equals(correct_password, password)) {
            return "INVALID_PASSWORD";
        }
        return "SUCCESS";
    }
}
