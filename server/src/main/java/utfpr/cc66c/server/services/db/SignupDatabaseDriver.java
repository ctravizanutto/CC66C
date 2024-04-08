package utfpr.cc66c.server.services.db;

import utfpr.cc66c.server.controllers.ServerController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupDatabaseDriver {
    public static boolean signupCandidate(String email, String password, String name) {
        var columns = "(\"email\", \"password\", \"name\")";
        var values = "('" + email + "', '" + password + "', '" + name + "')";
        var sql = "INSERT INTO candidates " + columns + " VALUES " + values;

        return querySignup(sql);
    }

    public static boolean signupRecruiter(String email, String password, String name, String description, String industry) {
        var columns = "(\"email\", \"password\", \"name\", \"description\", \"industry\")";
        var values = "('" + email + "', '" + password + "', '" + name + "', '" + description + "', '" + industry + "')";
        var sql = "INSERT INTO recruiters " + columns + " VALUES " + values;

        return querySignup(sql);
    }

    private static boolean querySignup(String sql) {
        var rs = 0;
        try {
            PreparedStatement stmt = ServerController.getDatabaseConnection().prepareStatement(sql);
            System.out.println("[DEBUG] Querying " + stmt);
            rs = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs > 0;
    }
}
