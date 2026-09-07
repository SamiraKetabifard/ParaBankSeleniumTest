package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();
    static {
        try {
            FileInputStream file = new FileInputStream(
                    "src/main/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config.properties");
        }
    }
    public static String getUrl() {
        return properties.getProperty("url");
    }
}