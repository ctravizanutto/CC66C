package utfpr.cc66c.server.services.db;

import utfpr.cc66c.server.controllers.ServerController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseDriver {
    public static ResultSet query(String sql) {
        try {
            PreparedStatement stmt = ServerController.getDatabaseConnection().prepareStatement(sql);
            System.out.println("[DEBUG] Querying " + stmt);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean update(String sql) {
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
