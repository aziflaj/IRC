package com.aziflaj.irc.server;

import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * The broker is a middleware connecting all the clients of a single server
 */
public class Broker {
    private static Broker instance = null;
    private LinkedList<PrintWriter> connections;

    private Broker() {
        connections = new LinkedList<>();
    }

    /**
     * Get an instance of the {@code Broker} class, turning it into a Singleton
     *
     * @return The instance of the singleton
     */
    public static Broker getInstance() {
        if (instance == null) {
            instance = new Broker();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Write a message to all the clients in the broker's list
     *
     * @param message The message to write
     */
    public void writeMessage(String message) {
        for (PrintWriter out : connections) {
            out.println(message);
            out.flush();
        }
    }

    /**
     * Add a new Client Output stream into the broker's list
     * @param conn The Output stream of a Client
     */
    public void add(PrintWriter conn) {
        connections.add(conn);
    }

    /**
     * Remove a Client Output stream from the broker's list
     * @param conn The Output stream of a Client
     */
    public void remove(PrintWriter conn) {
        connections.remove(conn);
    }
}
