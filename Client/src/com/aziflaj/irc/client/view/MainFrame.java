package com.aziflaj.irc.client.view;

import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    public static JTextArea messageThreadTextArea;
    public static JTextField newMessageTextField;

    public MainFrame() throws IOException {
        super("IRC Client");

        // init view variables
        messageThreadTextArea = new JTextArea(20, 30);
        messageThreadTextArea.setAutoscrolls(true);
//        JScrollPane scrollPane = new JScrollPane(messageThreadTextArea);
        messageThreadTextArea.setEditable(false);
        messageThreadTextArea.setLineWrap(true);
        messageThreadTextArea.setWrapStyleWord(true);

        newMessageTextField = new JTextField(30);
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(actionEvent -> {
            String message = newMessageTextField.getText();
            EventListener.onSendButtonClicked(message);
        });

        // put everything in its place
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.add(messageThreadTextArea);

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
