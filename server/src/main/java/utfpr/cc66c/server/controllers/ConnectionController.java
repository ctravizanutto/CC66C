package utfpr.cc66c.server.controllers;


import utfpr.cc66c.server.services.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionController extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private InetAddress addr;
    private int port;


    public ConnectionController(Socket socket) throws IOException {
        if (clientSocket == null) {
            clientSocket = socket;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            addr = clientSocket.getInetAddress();
            port = clientSocket.getPort();

            this.start();
        }
        System.out.printf("[INFO] %s:%s connected.\n", addr, port);
    }

    public void sendJSON(String response) {
        System.out.printf("[INFO] Sending response to %s:%s: %s\n", addr, port, response);
        out.println(response);
    }

    @Override
    public void run() {
        while (!clientSocket.isClosed()) {
            try {
                var request = in.readLine();
                if (request == null) {
                    System.out.printf("[INFO] %s:%s disconnected.\n", addr, port);
                    return;
                }
                System.out.printf("[INFO] Request incoming from %s:%s: %s\n", addr, port, request);
                sendJSON(RequestParser.parseJSON(request));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
