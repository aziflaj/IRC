package com.aziflaj.irc.client;

import com.aziflaj.irc.client.utils.PropertyReader;
import com.aziflaj.irc.client.view.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame app = null;
            try {
                String pageTitle = "IRC Client: " + PropertyReader.valueOf("username");
                app = new MainFrame(pageTitle);
                app.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
