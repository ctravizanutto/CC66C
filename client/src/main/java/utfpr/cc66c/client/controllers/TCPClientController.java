package utfpr.cc66c.client.controllers;

import java.io.*;
import java.net.Socket;

public class TCPClientController extends Thread{
    private Socket clientSocket;

    public TCPClientController(Integer port) {
        try {
            clientSocket = new Socket("0.0.0.0", port);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (!clientSocket.isClosed()) {
            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                var buffer = in.readLine();
                if (buffer == null) {
                    clientSocket.close();
                } else {
                    System.out.println(buffer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
