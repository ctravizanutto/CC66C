package utfpr.cc66c.server.controllers.auth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.server.services.db.DatabaseDriver;

public class SignupController {
    public static boolean signupCandidate(String email, String password, String name) {
        var columns = "(\"email\", \"password\", \"name\")";
        var values = "('" + email + "', '" + password + "', '" + name + "')";
        var sql = "INSERT INTO candidates " + columns + " VALUES " + values;

        return DatabaseDriver.update(sql);
    }

    public static boolean signupRecruiter(String email, String password, String name, String description, String industry) {
        var columns = "(\"email\", \"password\", \"name\", \"description\", \"industry\")";
        var values = "('" + email + "', '" + password + "', '" + name + "', '" + description + "', '" + industry + "')";
        var sql = "INSERT INTO recruiters " + columns + " VALUES " + values;

        return DatabaseDriver.update(sql);
    }

    public static String getSignupStatus(ObjectNode json) {
        var fields = JsonFields.getStringFields(json);
        var name = fields.get("name");
        var email = fields.get("email");
        var password = fields.get("password");
        var operation = fields.get("operation");

        var status = LoginController.getLoginStatus(operation, email, password);
        if (status.equals("INVALID_LOGIN")) {
            if (operation.contains("CANDIDATE")) {
                if (signupCandidate(email, password, name)) {
                    return "SUCCESS";
                }
            } else {
                var description = fields.get("description");
                var industry = fields.get("industry");
                if (signupRecruiter(email, password, name, description, industry)) {
                    return "SUCCESS";
                }
            }
        } else if (status.equals("SUCCESS")) {
            return "USER_EXISTS";
        }
        return status;
    }
}
