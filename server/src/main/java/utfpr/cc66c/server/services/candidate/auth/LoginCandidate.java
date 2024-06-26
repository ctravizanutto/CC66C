package utfpr.cc66c.server.services.candidate.auth;

import utfpr.cc66c.server.services.db.DatabaseDriver;

import java.sql.SQLException;

public class LoginCandidate {
    public static String getCandidateIdByEmail(String email) {
        var sql = "SELECT candidate_id FROM candidates WHERE email = \"" + email + "\"";
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;
            return resultSet.getString("candidate_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCandidateEmailByCandidateId(String candidate_id) {
        var sql = "SELECT email FROM candidates WHERE candidate_id = " + candidate_id;
        var resultSet = DatabaseDriver.query(sql);
        assert resultSet != null;
        try {
            return resultSet.getString("email");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCandidatePasswordByEmail(String email) {
        var sql = "SELECT password FROM candidates WHERE email = \"" + email + "\"";
        var resultSet = DatabaseDriver.query(sql);
        try {
            assert resultSet != null;
            return resultSet.getString("password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
