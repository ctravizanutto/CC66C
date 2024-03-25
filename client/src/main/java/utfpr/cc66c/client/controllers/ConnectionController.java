package utfpr.cc66c.client.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionController {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public ConnectionController(String addr, Integer port) {
        if (clientSocket == null) {
            try {
                clientSocket = new Socket(addr, port);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException("[ERROR] Invalid ip address.");
            }
        }
    }

    public void shutdown() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[INFO] Client closed.");
        System.exit(0);
    }

    public static String requestResponse(String json) {
        System.out.println("[INFO] Sending request: " + json);
        out.println(json);
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] Couldn't receive response from server.");
        }
    }

}
