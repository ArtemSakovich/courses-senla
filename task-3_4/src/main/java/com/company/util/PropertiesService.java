package com.company.util;

import com.company.api.util.IPropertiesService;
import com.company.injection.annotation.DependencyClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
@DependencyClass
public class PropertiesService implements IPropertiesService {

    private Properties properties;
    private static final Logger log = Logger.getLogger(PropertiesService.class.getName());

    private PropertiesService() {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("hoteladministrator.properties")) {
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