package utfpr.cc66c.server.controllers;

import utfpr.cc66c.server.services.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionController extends Thread {
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final InetAddress addr;
    private final int port;


    public ConnectionController(Socket socket) throws IOException {
        clientSocket = socket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        addr = clientSocket.getInetAddress();
        port = clientSocket.getPort();

        this.start();
        System.out.printf("[INFO] %s:%s connected.\n", addr, port);
//        DashboardViewFactory.addClient(addr.toString(), port);
    }

    private void sendJson(String response) {
        System.out.printf("[INFO] Sending response to %s:%s: %s\n", addr, port, response);
        out.println(response);
    }

    public void shutdown() {
        try {
            clientSocket.close();
            in.close();
            out.close();
//            DashboardViewFactory.removeClient(addr.toString(), port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (!clientSocket.isClosed()) {
            try {
                var request = in.readLine();
                if (request == null) {
                    System.out.printf("[INFO] %s:%s disconnected.\n", addr, port);
                    shutdown();
                    return;
                }
                System.out.printf("[INFO] Request incoming from %s:%s: %s\n", addr, port, request);
                sendJson(RequestParser.parseJson(request));
            } catch (IOException e) {
                this.shutdown();
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
