package com.aziflaj.irc.client.logic;

import java.io.*;
import java.net.Socket;


public class SocketClient {
    private static SocketClient instance = null;
    PrintWriter out; // A way to print into the MainFrame
    BufferedReader in; // reading from the MainFrame

    public SocketClient(String host, int port) throws IOException {
        Socket client = new Socket(host, port);
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public static SocketClient getInstance(){
        if (instance == null) {
            try {
                instance = new SocketClient("localhost", 4444);
            } catch (IOException e) {
                System.err.println("Can't create instance");
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void sendToServer(String message) {
        out.println(message);
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
