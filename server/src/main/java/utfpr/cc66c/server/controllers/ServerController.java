package utfpr.cc66c.server.controllers;

import utfpr.cc66c.server.services.db.DatabaseService;
import utfpr.cc66c.server.views.DashboardViewFactory;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;

public class ServerController extends Thread {
    private final ServerSocket serverSocket;
    private static DatabaseService databaseService;

    public ServerController() {
        String ip;
        int port;
        try {
            serverSocket = new ServerSocket(21234);
            ip = Inet4Address.getLocalHost().getHostAddress();
            port = serverSocket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String info = "[INFO] Server listening at " + ip + ":" + port;
        System.out.println(info);

        databaseService = new DatabaseService();
        DashboardViewFactory.setIp(ip);

        start();
    }

    public static Connection getDatabaseConnection() {
        return databaseService.getConnection();
    }

    public void shutdown() {
        try {
            interrupt();
            serverSocket.close();
            getDatabaseConnection().close();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(!serverSocket.isClosed()) {
            try {
                var conn = serverSocket.accept();
                if (conn != null)
                    new ConnectionController(conn);
            } catch (IOException e) {
                if (e instanceof SocketException) {
                    return;
                }
                throw new RuntimeException(e);
            }
        }
    }
}
