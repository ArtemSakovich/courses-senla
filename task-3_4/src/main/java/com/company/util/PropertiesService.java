package com.company.util;

import com.company.api.util.IPropertiesService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesService implements IPropertiesService {
    private static PropertiesService instance;
    private Properties properties;
    private static final Logger log = Logger.getLogger(PropertiesService.class.getName());

    public static PropertiesService getInstance() {
        if (instance == null) {
            instance = new PropertiesService();
        }
        return instance;
    }

    private PropertiesService() {
        try (FileInputStream input = new FileInputStream("hoteladministrator.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Property file not found");
        }
    }

    public String getPropertyValueByKey(String key) {
        return properties.getProperty(key);
    }
}