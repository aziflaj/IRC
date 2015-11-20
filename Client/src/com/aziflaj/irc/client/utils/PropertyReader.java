package com.aziflaj.irc.client.utils;

import java.io.*;
import java.util.Properties;

public final class PropertyReader {
    private static final String PROPERTIES_DIR = "Client/resources/";
    private static final String PROPERTIES_FILE = "client.properties";
    private static Properties instance = null;

    public static Properties getInstance() throws IOException {
        if (instance == null) {
            instance = new Properties();
            FileReader in = new FileReader(PROPERTIES_DIR + PROPERTIES_FILE);
            instance.load(in);
        }
        return instance;
    }

    public static String valueOf(String key) {
        try {
            if (!getInstance().containsKey(key)) {
                return "";
            } else {
                return getInstance().getProperty(key);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find " + PROPERTIES_FILE);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
