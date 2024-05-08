package utfpr.cc66c.server.services.candidate.profile;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LookupCandidate {
    public static void lookupToData(String id, ObjectNode data) {
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
}
