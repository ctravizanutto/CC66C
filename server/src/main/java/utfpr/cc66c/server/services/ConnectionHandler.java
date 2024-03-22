package utfpr.cc66c.server.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public ConnectionHandler(Socket socket) throws IOException {
        if (clientSocket == null) {
            clientSocket = socket;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
    }

    public static void sendJSON(String response) {
        System.out.println("[INFO] Sending response: " + response);
        out.println(response);
    }

    @Override
    public void run() {
        while (!clientSocket.isClosed()) {
            try {
                var request = in.readLine();
                if (request == null) {
                    clientSocket.close();
                    interrupt();
                }
                System.out.println("[INFO] Request incoming: " + request);
                JSONParser.parseJSON(request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
