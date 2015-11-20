package com.aziflaj.irc.protocol;

/**
 * Definition of the IRC protocol
 */
public interface Protocol {
    /**
     * Process the input sent from a client
     *
     * @param input The input sent from a client
     * @return A string output based on the input
     */
    String processInput(String input);
}
