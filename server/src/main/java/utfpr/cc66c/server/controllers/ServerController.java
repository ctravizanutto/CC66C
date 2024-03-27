package utfpr.cc66c.server.controllers;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;

public class ServerController extends Thread {
    private final ServerSocket serverSocket;

    public ServerController() {
        try {
            serverSocket = new ServerSocket(21234);
            start();
            System.out.printf("[INFO] Server listening at %s:%s\n", Inet4Address.getLocalHost().getHostAddress(), serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException();
        }
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
}
