package com.aziflaj.irc.client.utils;

import java.io.*;
import java.util.Properties;

/**
 * A helper class that reads the *.properties file
 */
public final class PropertyReader {
    private static final String PROPERTIES_DIR = "resources/";
    private static final String PROPERTIES_FILE = "client.properties";
    private static Properties instance = null;

    /**
     * By creating a single PropertyReader instance (i.e. Singleton) we make sure
     * not to create more than one reader of the same file
     *
     * @return The singleton instance
     * @throws IOException If the properties file doesn't exist
     */
    public static Properties getInstance() throws IOException {
        if (instance == null) {
            instance = new Properties();
            FileReader in = new FileReader(PROPERTIES_DIR + PROPERTIES_FILE);
            instance.load(in);
        }
        return instance;
    }

    /**
     * Parses the properties file and returns a value from a given key
     *
     * @param key The given key to search in the file
     * @return The value corresponding to the given key, or an empty string if not found
     */
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
