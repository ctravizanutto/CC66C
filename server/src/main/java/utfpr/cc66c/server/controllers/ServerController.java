package utfpr.cc66c.server.controllers;

import utfpr.cc66c.server.ServerApplication;
import utfpr.cc66c.server.services.db.DatabaseService;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.sql.Connection;

public class ServerController extends Thread {
    private final ServerSocket serverSocket;
    private static DatabaseService databaseService;

    public ServerController() {
        String ip;
        int port;
        try {
            serverSocket = new ServerSocket(21234);
            start();
            ip = Inet4Address.getLocalHost().getHostAddress();
            port = serverSocket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String info = "[INFO] Server listening at " + ip + ":" + port;
        System.out.println(info);
        ServerApplication.getServerApplicationController().ipLabel.setText(info);

        databaseService = new DatabaseService();
    }

    public void shutdown() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(!serverSocket.isClosed()) {
            try {
                var conn = serverSocket.accept();
                if (conn != null)
                    new ServerConnectionController(conn);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static Connection getDatabaseConnection() {
        return databaseService.getConnection();
    }
}
