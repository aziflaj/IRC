package com.aziflaj.irc.client.logic;

import com.aziflaj.irc.client.utils.PropertyReader;
import com.aziflaj.irc.client.view.MainFrame;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.io.*;
import java.net.Socket;

/**
 * The IRC Client embedded in the Client application. It deals with reading from
 * and writing to the Server and also updating the UI using Threads
 */
public class IrcClient {
    private static IrcClient instance = null;
    PrintWriter out; // A way to print into the MainFrame
    BufferedReader in; // reading from the MainFrame

    /**
     * Creates an instance of the IrcClient class. It is private, since it is
     * wrapped in a Singleton and there is no need for multiple client sockets
     * in a client application
     *
     * @param host The Server host name
     * @param port The Server port, opened for the communication
     * @throws IOException Thrown if any of the I/O stream readers can't be created
     */
    private IrcClient(String host, int port) throws IOException {
        Socket client = new Socket(host, port);
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    /**
     * If the singleton object is not created, this is the method that creates it.
     * If the object is already created, it is returned. The server host and port
     * are read from the client.properties file using the {@code PropertyReader}
     * class. If any of the entries is not set properly (e.g. the port is not an integer)
     * it will throw an exception
     *
     * @return the (one and only) instance of the IrcClient
     * @throws IOException If the data is not set properly in the client.properties file
     */
    public static IrcClient getInstance() throws IOException {
        if (instance == null) {
            String host = PropertyReader.valueOf("host");
            int port = Integer.parseInt(PropertyReader.valueOf("port"));

            instance = new IrcClient(host, port);
            // start reading from the server
            instance.readFromServer();
        }
        return instance;
    }

    /**
     * Sends a String message to the server, so it can then be passed to all the other clients
     *
     * @param message The message to send to the server
     */
    public void sendToServer(String message) {
        out.println(message);
        out.flush();
    }

    /**
     * The method reads from the server and updates the message thread.
     */
    public void readFromServer() {
        new Thread(() -> {
            String line;
            while (true) {
                try {
                    if ((line = in.readLine()) != null) {
                        StyledDocument doc = MainFrame.messageThreadTextPane.getStyledDocument();
                        try {
                            doc.insertString(doc.getLength(), line + "\n", null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
