package com.aziflaj.irc.client.view;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    public static JTextPane messageThreadTextPane;
    public static JTextField newMessageTextField;

    public MainFrame() throws IOException {
        super("IRC Client");

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
            EventListener.onSendButtonClicked(message);
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

        this.add(masterPanel);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); //center
    }
}
