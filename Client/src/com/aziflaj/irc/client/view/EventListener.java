package com.aziflaj.irc.client.view;

import com.aziflaj.irc.client.logic.SocketClient;

public class EventListener {

    public static void onSendButtonClicked(String message) {
        SocketClient.getInstance().sendToServer(message);
        System.out.println(message);
        MainFrame.newMessageTextField.setText("");
        MainFrame.messageThreadTextArea.append(message + "\n");
        System.out.println("sending");
    }
}
