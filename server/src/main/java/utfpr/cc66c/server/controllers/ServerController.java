package utfpr.cc66c.server.controllers;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerController extends Thread {
    private final ServerSocket serverSocket;

    public ServerController() {
        try {
            serverSocket = new ServerSocket(21234);
            System.out.printf("[INFO] Server listening at %s:%s", serverSocket.getInetAddress(), serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void shutdown() {

    }

    @Override
    public void run() {
        while(!serverSocket.isClosed()) {
            try {
                var conn = serverSocket.accept();
                new ConnectionController(conn);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
