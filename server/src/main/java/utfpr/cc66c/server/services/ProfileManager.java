package utfpr.cc66c.server.services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.core.serializers.JsonFields;
import utfpr.cc66c.core.validators.JWTValidator;
import utfpr.cc66c.server.controllers.ServerController;
import utfpr.cc66c.server.controllers.auth.AuthController;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class ProfileManager {
    public static String lookup(ObjectNode json) {
        if (!AuthController.validateToken(json)) {
            return json.toString();
        }
        json.put("status", "SUCCESS");

        var fields = JsonFields.getStringFields(json);
        var operation = fields.get("operation");
        var token = fields.get("token");
        var data = (ObjectNode) json.get("data");

        var id = JWTValidator.getIdClaim(token);

        if (operation.equals("LOOKUP_CANDIDATE")) {
            lookupCandidate(id, data);
        } else {
            lookupRecruiter(id, data);
        }

        return json.toString();
    }

    private static void lookupCandidate(String id, ObjectNode data) {
        var sql = "SELECT email, password, name FROM candidates WHERE candidate_id =" + id;
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;

            var email = resultSet.getString("email");
            var password = resultSet.getString("password");
            var name = resultSet.getString("name");

            data.put("email", email);
            data.put("password", password);
            data.put("name", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void lookupRecruiter(String id, ObjectNode data) {
        var sql = "SELECT email, password, name, description, industry FROM recruiters WHERE recruiter_id =" + id;
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;

            var email = resultSet.getString("email");
            var password = resultSet.getString("password");
            var name = resultSet.getString("name");
            var description = resultSet.getString("description");
            var industry = resultSet.getString("industry");

            data.put("email", email);
            data.put("password", password);
            data.put("name", name);
            data.put("description", description);
            data.put("industry", industry);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String update(ObjectNode json) {
        return "";
    }

    public static String delete(ObjectNode json) {
        var fields = JsonFields.getStringFields(json);
        if (!AuthController.validateToken(json)) {
            return json.toString();
        }
        json.put("status", "SUCCESS");

        var operation = fields.get("operation");
        var token = fields.get("token");

        var id = JWTValidator.getIdClaim(token);

        if (operation.equals("DELETE_ACCOUNT_CANDIDATE")) {
            deleteCandidate(id);
        } else {
            return "[DEBUG] TODO";
        }

        ServerController.removeSession(token);
        return json.toString();
    }

    private static void deleteCandidate(String id) {
        var sql = "DELETE FROM candidates WHERE candidate_id =" + id;
        var resultSet = DatabaseDriver.query(sql);
        assert resultSet != null;
    }
}
