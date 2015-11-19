package com.aziflaj.irc.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 4444);
            PrintWriter outputStream = new PrintWriter(client.getOutputStream(), true);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in));
            // Scanner stdIn = new Scanner(System.in);
            while (true) {
                System.out.print(">>> ");
                String input = stdInput.readLine();
                String input = stdIn.nextLine();
                outputStream.println(input);
                if (input.equals("quit")) {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
