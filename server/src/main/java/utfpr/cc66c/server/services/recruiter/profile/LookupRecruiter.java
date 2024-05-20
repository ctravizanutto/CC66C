package utfpr.cc66c.server.services.recruiter.profile;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupRecruiter {
    public static void lookupToData(String id, ObjectNode data) {
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
}
