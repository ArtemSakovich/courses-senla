package com.company.configuration;

import com.company.configuration.annotation.ConfigClass;
import com.company.configuration.annotation.ConfigProperty;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigService {
    private static ConfigService instance;
    private Class<?> configClass;
    private String configPath;

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    public void setValue(Object instanceOfClass) throws IllegalAccessException, FileNotFoundException {
        this.configClass = instanceOfClass.getClass();
        final ConfigClass annotation = configClass.getAnnotation(ConfigClass.class);
        if (!(annotation == null)) {
            this.configPath = annotation.configPath();
            addFieldValue(instanceOfClass);
        }
    }

    public void addFieldValue(Object instanceOfClass) throws IllegalAccessException, FileNotFoundException {
        final List<Field> annotatedFields = Arrays.stream(configClass.getDeclaredFields())
                .filter(i -> i.isAnnotationPresent(ConfigProperty.class))
                .collect(Collectors.toList());
        for (Field field : annotatedFields) {
            final Config config = new Config(field);
            final Properties properties = ConfigReader.getInstance().readConfig(configPath + config.getConfigName());
            final Object value = customConverter(field, properties.getProperty(config.getPropertyName()));
            config.getField().setAccessible(true);
            config.getField().set(instanceOfClass, value);
            config.getField().setAccessible(false);
        }
    }

    private Object customConverter (Field field, String variable) {
        final String variableType = field.getType().getSimpleName();
        return switch (variableType) {
            case "boolean" -> Boolean.parseBoolean(variable);
            case "int" -> Integer.parseInt(variable);
            default -> variable;
        };
    }
}
