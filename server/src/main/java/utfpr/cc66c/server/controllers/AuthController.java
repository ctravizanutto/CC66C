package utfpr.cc66c.server.controllers;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.LoginValidator;
import utfpr.cc66c.server.services.db.LoginDatabaseDriver;
import utfpr.cc66c.server.services.db.SignupDatabaseDriver;

import java.util.Objects;

public class AuthController {
    public static String login(ObjectNode json) {
        var fields = JsonFields.getAllFields(json);
        var status = getLoginStatus(fields.get("operation"), fields.get("email"), fields.get("password"));

        json.set("data", JsonNodeFactory.instance.objectNode());
        json.put("status", status);
        return json.toString();
    }

    private static String getLoginStatus(String operation, String email, String password) {
        if (LoginValidator.invalidEmail(email)) {
            return "INVALID_EMAIL";
        } else if (LoginValidator.invalidPassword(password)) {
            return "INVALID_PASSWORD";
        }

        String dbPassword;
        if (operation.equals("LOGIN_CANDIDATE")) {
            dbPassword = LoginDatabaseDriver.getCandidatePasswordByEmail(email);
        } else {
            dbPassword = LoginDatabaseDriver.getRecruiterPasswordByEmail(email);
        }
        if (dbPassword == null) {
            return "USER_NOT_FOUND";
        } else if (!Objects.equals(dbPassword, password)) {
            return "INVALID_PASSWORD";
        } else {
            return "SUCCESS";
        }
    }

    public static String signup(ObjectNode json) {
        var status = getSignupStatus(json);
        
        json.put("status", status);
        json.set("data", JsonNodeFactory.instance.objectNode());
        return json.toString();
    }

    private static String getSignupStatus(ObjectNode json) {
        var fields = JsonFields.getAllFields(json);
        var name = fields.get("name");
        var email = fields.get("email");
        var password = fields.get("password");
        var status = getLoginStatus(email, password, name);
        if (status.equals("USER_NOT_FOUND")) {
            if (fields.get("operation").equals("SIGNUP_CANDIDATE")) {
                if (SignupDatabaseDriver.signupCandidate(email, password, name)) {
                    return "SUCCESS";
                }
            } else {
                var description = fields.get("description");
                var industry = fields.get("industry");
                if (SignupDatabaseDriver.signupRecruiter(email, password, name, description, industry)) {
                    return "SUCCESS";
                }
            }
        }
        return status;
    }

}
