package com.aziflaj.irc.protocol;

import java.util.Date;

/**
 * The implementation of the {@code Protocol} interface
 */
public class ProtocolImpl implements Protocol {
    /**
     * Process the input sent from a client
     *
     * @param input The input sent from a client
     * @return A string output based on the input
     */
    @Override
    public String processInput(String input) {
        switch (input) {
            case "\\quit":
            case "\\exit":
                return "Bye";

            case "\\now":
                Date now = new Date();
                return now.toString();

            default:
                return input;
        }
    }
}
