package com.company.configuration;

import com.company.configuration.annotation.ConfigProperty;

import java.lang.reflect.Field;

public class Config {
    private final Field field;
    private final String configName;
    private final String propertyName;

    public Config(Field field) {
        this.field = field;
        this.configName = field.getAnnotation(ConfigProperty.class).configName().toLowerCase();
        this.propertyName = field.getName();
    }

    public Field getField() {
        return field;
    }

    public String getConfigName() {
        return configName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
