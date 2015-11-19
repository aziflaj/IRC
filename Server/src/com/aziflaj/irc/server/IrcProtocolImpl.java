package com.aziflaj.irc.server;

public class IrcProtocolImpl implements IrcProtocol {
    @Override
    public String processInput(String input) {
        switch (input) {
            case "\\quit":
                return "Bye";

            default:
                return "Command unknown";
        }
    }
}
