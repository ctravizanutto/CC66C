package utfpr.cc66c.server.services.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class LoginDatabaseDriver extends DatabaseService {
    public static String getCandidatePasswordByEmail(String email) {
        var sql = "SELECT password FROM candidates WHERE email = ?";
        String password = null;
        try {
            PreparedStatement stmt = DatabaseService.c.prepareStatement(sql);
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            System.out.println("[DEBUG] Querying " + stmt);
            password = rs.getString("password");
        } catch (SQLException ignored) {
        }
        return password;
    }

    public static String getRecruiterPasswordByEmail(String email) {
        var sql = "SELECT password FROM recruiters WHERE email = ?";
        String password = null;
        try {
            PreparedStatement stmt = DatabaseService.c.prepareStatement(sql);
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            System.out.println("[DEBUG] Querying " + stmt);
            password = rs.getString("password");
        } catch (SQLException ignored) {
        }
        return password;
    }

    public static boolean checkCandidateByEmail(String email) {
        var sql = "SELECT COUNT(1) FROM candidates WHERE email = ?";
        try {
            PreparedStatement stmt = DatabaseService.c.prepareStatement(sql);
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            System.out.println("[DEBUG] Querying " + stmt);
            if (Objects.equals(rs.getString("COUNT(1)"), "1")) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
