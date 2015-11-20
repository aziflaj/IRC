package com.aziflaj.irc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * An IRC Server with Sockets
 */
public class Server {
    public static int clientNumber = 0;

    public static void main(String[] args) {
        Broker broker = Broker.getInstance();
        try (ServerSocket serverSocket = new ServerSocket(4444)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.printf("Client %d ready\n", (++clientNumber));
                MultiClientHandler handler = new MultiClientHandler(clientSocket, clientNumber, broker);
                new Thread(handler).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
