package com.aziflaj.irc.client.view;

import com.aziflaj.irc.client.logic.IrcClient;
import com.aziflaj.irc.client.utils.PropertyReader;

import javax.swing.text.*;

public class EventListener {

    public static void onSendButtonClicked(String message) {
        String username = PropertyReader.valueOf("username");
        message = String.format("%s: %s", username, message);

        IrcClient.getInstance().sendToServer(message);
        System.out.println(message);
        MainFrame.newMessageTextField.setText("");

        StyledDocument doc = MainFrame.messageThreadTextPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), message + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
