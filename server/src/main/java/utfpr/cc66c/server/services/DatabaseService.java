package utfpr.cc66c.server.services;

import utfpr.cc66c.core.models.LoginModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {
    private static Connection c;

    public DatabaseService() {
        if (c == null) {
            var url = "jdbc:sqlite:server/src/main/resources/utfpr/cc66c/server/db/cc66c.db";
            try {
                c = DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static LoginModel getUser(String email) {
        var sql = "SELECT name, password FROM candidates WHERE email = ?";
        String password = null;
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, email.substring(1, email.length() - 1));
            var rs = stmt.executeQuery();
            System.out.println("[DEBUG] Querying " + stmt);
            String name;
            while (rs.next()) {
                name = rs.getString("name");
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new LoginModel(email, password, null);
    }

}
