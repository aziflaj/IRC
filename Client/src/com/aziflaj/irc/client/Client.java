package com.aziflaj.irc.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (
            Socket client = new Socket("localhost", 4444);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            while (true) {
                System.out.print(">>> ");
                String input = stdIn.readLine();
                out.println(input);
                if (input.equals("\\quit")) {
                    client.close();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
