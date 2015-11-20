package com.aziflaj.irc.client.view;

import com.aziflaj.irc.client.logic.IrcClient;
import com.aziflaj.irc.client.utils.PropertyReader;

/**
 * The one and only: Event Handler
 */
public class EventListener {

    /**
     * Handle clicks on the Send button
     *
     * @param message The message to send when the Send button is clicked
     */
    public static void onSendButtonClicked(String message) {
        String username = PropertyReader.valueOf("username");
        message = String.format("%s: %s", username, message);

        IrcClient.getInstance().sendToServer(message);
        System.out.println(message);
        MainFrame.newMessageTextField.setText("");
    }
}
