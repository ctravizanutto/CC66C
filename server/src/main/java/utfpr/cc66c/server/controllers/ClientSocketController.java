package utfpr.cc66c.server.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketController extends Thread {
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    public ClientSocketController(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("[DEBUG] connected");
        out.println("Connected.");
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
