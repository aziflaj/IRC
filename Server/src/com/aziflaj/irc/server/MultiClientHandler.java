package com.aziflaj.irc.server;

import com.aziflaj.irc.protocol.Protocol;
import com.aziflaj.irc.protocol.ProtocolImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiClientHandler implements Runnable {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;
    private int clientNumber;

    public MultiClientHandler(Socket client, int clientNumber) throws IOException {
        this.clientNumber = clientNumber;
        this.client = client;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        String inputLine;
        String output;

        Protocol protocol = new ProtocolImpl();
        try {
            while ((inputLine = in.readLine()) != null) {
                System.out.printf("Client %s says: %s\n", this.clientNumber, inputLine);
                output = protocol.processInput(inputLine);
                out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
