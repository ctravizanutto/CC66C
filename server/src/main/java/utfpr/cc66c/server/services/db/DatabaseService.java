package utfpr.cc66c.server.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    private final Connection c;

    public DatabaseService() {
        var url = "jdbc:sqlite:server/src/main/resources/utfpr/cc66c/server/db/cc66c.db";
        try {
            c = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return c;
    }

}
