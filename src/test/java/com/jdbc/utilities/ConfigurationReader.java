package com.jdbc.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.jdbc.utilities.DBUtility.url;

public class ConfigurationReader {

    private static Properties configFile;

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream("configuration.properties");
            configFile = new Properties();
            configFile.load(fileInputStream);
        } catch (IOException e) {

        }
    }

    public static String getProperty(String key) {
        return configFile.getProperty(key);
    }

    public static void main(String[] args) {
        String URL = getProperty("JDBC_URL");
        String username = getProperty("JDBC_username");
        String password = getProperty("JDBC_password");

        System.out.println(URL);
        System.out.println(username);
        System.out.println(password);
    }
}
