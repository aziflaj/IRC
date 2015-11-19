package com.aziflaj.irc.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            System.out.println("Client ready");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client says: " + inputLine);
                if (inputLine.equals("quit")) {
                    in.close();
                    out.close();
                    serverSocket.close();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
