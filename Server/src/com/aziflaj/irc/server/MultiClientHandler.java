package com.aziflaj.irc.server;

import com.aziflaj.irc.protocol.Protocol;
import com.aziflaj.irc.protocol.ProtocolImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A Runnable handler for handling multiple clients connected to the same IRC server.
 * Each client gets a response in its separate thread
 */
public class MultiClientHandler implements Runnable {
    private Broker broker;
    private PrintWriter out;
    private BufferedReader in;
    private int clientNumber;

    /**
     * Create a Runnable instance for each client thread and initialize the I/O streams
     *
     * @param client       The client to be handled
     * @param clientNumber An index number for the client
     * @param broker       The broker instance, connecting all the clients
     * @throws IOException An {@code IOException} is thrown if the reader or the writer can't be created
     */
    public MultiClientHandler(Socket client, int clientNumber, Broker broker) throws IOException {
        this.clientNumber = clientNumber;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.broker = broker;
    }

    /**
     * Overriding the {@code run()} method to create a thread for each client
     */
    @Override
    public void run() {
        String inputLine;
        String output;

        Protocol protocol = new ProtocolImpl();
        try {
            broker.add(out);
            while ((inputLine = in.readLine()) != null) {
                System.out.printf("Client %s says: %s\n", this.clientNumber, inputLine);
                output = protocol.processInput(inputLine);
                broker.writeMessage(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.broker.remove(out);
        }
    }
}
