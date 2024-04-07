package utfpr.cc66c.server.controllers;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.LoginDatabaseDriver;

import java.util.Objects;

public class AuthController {
    public static String login(ObjectNode json) {
        var operation = json.get("operation").asText();
        var email = json.get("data").get("email").asText();
        var password = json.get("data").get("password").asText();
        json.set("data", JsonNodeFactory.instance.objectNode());

        String dbPassword, status;
        if (operation.equals("LOGIN_CANDIDATE")) {
            dbPassword = LoginDatabaseDriver.getCandidatePasswordByEmail(email);
        } else {
            dbPassword = LoginDatabaseDriver.getRecruiterPasswordByEmail(email);
        }
        if (dbPassword == null) {
            status = "USER_NOT_FOUND";
        } else if (!Objects.equals(dbPassword, password)) {
            status = "INVALID_PASSWORD";
        } else {
            status = "SUCCESS";
        }
        json.put("status", status);
        return json.toString();
    }

    public static String singup(ObjectNode json) {
        var operation = json.get("operation").asText();
        var email = json.get("email").asText();
        var password = json.get("password").asText();


        return "";
    }

}
