package utfpr.cc66c.server.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    public ConnectionHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        while (!clientSocket.isClosed()) {
            try {
                var message = in.readLine();
                if (message == null) {
                    clientSocket.close();
                    continue;
                }
                System.out.println("[INFO] Message incoming: " + message);
                out.println(message.toUpperCase());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
