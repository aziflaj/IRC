package com.aziflaj.irc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int clientNumber = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.printf("Client %d ready\n", (++clientNumber));
                MultiClientHandler handler = new MultiClientHandler(clientSocket, clientNumber);
                new Thread(handler).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
