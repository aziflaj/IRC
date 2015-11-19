package com.aziflaj.irc.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (
            Socket client = new Socket("localhost", 4444);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))
        ) {
            while (true) {
                System.out.print(">>> ");
                String input = stdIn.readLine();
                out.println(input);
                String fromServer = in.readLine();

                if (input.equals("\\quit")) {
                    client.close();
                    break;
                }

                if (!fromServer.equals("Command unknown")) {
                    System.out.println(fromServer);
                }
            }

        } catch (ConnectException e) {
            System.err.println("Can't connect to the server right now. Please try again later");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
