package com.aziflaj.irc.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * UI wrapper for the IRC Client
 */
public class MainFrame extends JFrame {
    public static JTextPane messageThreadTextPane;
    public static JTextField newMessageTextField;

    public MainFrame(String title) throws IOException {
        // init view variables
        messageThreadTextPane = new JTextPane();
        messageThreadTextPane.setAutoscrolls(true);
        messageThreadTextPane.setEditable(false);
        messageThreadTextPane.setPreferredSize(new Dimension(350, 250));
        JScrollPane scrollPane = new JScrollPane(messageThreadTextPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        newMessageTextField = new JTextField(30);
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(actionEvent -> {
            String message = newMessageTextField.getText();
            try {
                EventListener.onSendButtonClicked(message);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        newMessageTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {}

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    String message = newMessageTextField.getText();
                    try {
                        EventListener.onSendButtonClicked(message);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // put everything in its place
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.add(messageThreadTextPane);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.X_AXIS));
        messagePanel.add(newMessageTextField);
        messagePanel.add(sendButton);

        masterPanel.add(messagePanel);

        // customize the UI
        this.setTitle(title);
        this.add(masterPanel);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //center
    }
}
