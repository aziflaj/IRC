package com.aziflaj.irc.client.view;

public class EventListener {

    public static void onSendButtonClicked(String message) {
        System.out.println(message);
        MainFrame.newMessageTextField.setText("");
        MainFrame.messageThreadTextArea.append(message + "\n");
        System.out.println("sending");
    }
}
