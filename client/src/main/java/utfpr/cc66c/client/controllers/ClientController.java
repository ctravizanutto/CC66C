package utfpr.cc66c.client.controllers;

import java.io.*;
import java.net.Socket;

public class ClientController extends Thread{
    private static Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public ClientController(Integer port) {
        if (clientSocket == null) {
            try {
                clientSocket = new Socket("0.0.0.0", port);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String echoText(String message) {
        out.println(message);
        try {
            var retMessage =  in.readLine();
            return retMessage != null ? retMessage : "";
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    @Override
    public void run() {

    }
}
