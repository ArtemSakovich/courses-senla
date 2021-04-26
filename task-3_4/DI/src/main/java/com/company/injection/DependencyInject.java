package com.company.injection;

import com.company.configuration.ConfigReader;
import org.reflections.Reflections;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;

public class DependencyInject {
    private static DependencyInject instance;
    private static Reflections scanner;

    public static DependencyInject getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new DependencyInject();
            final Properties properties = ConfigReader.getInstance().readConfig("hoteladministrator.properties");
            scanner = new Reflections(properties.getProperty("packageNameToScan"));
        }
        return instance;
    }

    public Class<?> injection(Field field) throws NoSuchMethodException {
        if (field.getType().isInterface()) {
            Class<?> ifc = field.getType();
            Set<Class<?>> classes = (Set<Class<?>>) scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                throw new IllegalArgumentException(ifc + " has 0 or more than 1 impl");
            }
            return classes.iterator().next();
        }
        else {
            return field.getType();
        }
    }
}
