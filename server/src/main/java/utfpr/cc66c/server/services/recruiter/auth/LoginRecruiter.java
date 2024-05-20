package utfpr.cc66c.server.services.recruiter.auth;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LoginRecruiter {
    public static String getRecruiterIdByEmail(String email) {
        var sql = "SELECT recruiter_id FROM recruiters WHERE email = \"" + email + "\"";
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;
            return resultSet.getString("recruiter_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRecruiterPasswordByEmail(String email) {
        var sql = "SELECT password FROM recruiters WHERE email = \"" + email + "\"";
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;
            return resultSet.getString("password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
