package com.aziflaj.irc.protocol;

import java.util.Date;

public class ProtocolImpl implements Protocol {
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
                return "Command unknown";
        }
    }
}
