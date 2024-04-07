package utfpr.cc66c.server.services.db;

import utfpr.cc66c.server.controllers.ServerController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDatabaseDriver extends DatabaseService {
    public static String getCandidatePasswordByEmail(String email) {
        var sql = "SELECT password FROM candidates WHERE email = ?";
        return queryLogin(email, sql);
    }

    public static String getRecruiterPasswordByEmail(String email) {
        var sql = "SELECT password FROM recruiters WHERE email = ?";
        return queryLogin(email, sql);
    }

    private static String queryLogin(String email, String sql) {
        try {
            PreparedStatement stmt = ServerController.getDatabaseConnection().prepareStatement(sql);
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            System.out.println("[DEBUG] Querying " + stmt);
            return rs.getString("password");
        } catch (SQLException ignored) {
            return null;
        }
    }

}
