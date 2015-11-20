package com.aziflaj.irc.client.logic;

import com.aziflaj.irc.client.utils.PropertyReader;

import java.io.*;
import java.net.Socket;


public class IrcClient {
    private static IrcClient instance = null;
    PrintWriter out; // A way to print into the MainFrame
    BufferedReader in; // reading from the MainFrame

    public IrcClient(String host, int port) throws IOException {
        Socket client = new Socket(host, port);
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public static IrcClient getInstance(){
        if (instance == null) {
            try {
                String host = PropertyReader.valueOf("host");
                int port = Integer.parseInt(PropertyReader.valueOf("port"));

                instance = new IrcClient(host, port);
            } catch (IOException e) {
                System.err.println("Can't create instance");
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void sendToServer(String message) {
        out.println(PropertyReader.valueOf("username") + ": " + message);
        out.flush();
    }

    public void readFromServer() {
        new Thread(() -> {
            String line;
            while (true) {
                try {
                    if ((line = in.readLine()) != null) {
                        // TODO: update view
                        System.out.println(line);
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
