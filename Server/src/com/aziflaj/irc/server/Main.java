package com.aziflaj.irc.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Server ready");

            Socket clientSocket = serverSocket.accept();

            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Client ready");
            while (true) {
                String input = inputStream.readLine();
                System.out.println("Client says: " + input);
                if (input.equals("quit")) {
                    inputStream.close();
                    serverSocket.close();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
