package utfpr.cc66c.client.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnectionController {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public ClientConnectionController(String addr, Integer port) {
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
            in.close();
            out.close();
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
            var response = in.readLine();
            if (response == null) {
                throw new RuntimeException("[ERROR] Response is null.");
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] Couldn't receive response from server.");
        }
    }

}
