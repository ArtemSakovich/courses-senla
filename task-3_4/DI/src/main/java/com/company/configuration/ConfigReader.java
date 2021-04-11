package com.company.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;
    private static final Logger log = Logger.getLogger(ConfigReader.class.getName());

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public Properties readConfig(String path) {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(path)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Property file not found");
        }
        return properties;
    }
}