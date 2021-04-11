package com.company.injection;

import com.company.configuration.ConfigReader;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DependencyController {
    private static final Logger log = Logger.getLogger(ConfigReader.class.getName());

    public static void setDependency(Class<?> clazz)  {
        try {
            DependencyService.getInstance().setVariable(clazz);
            DependencyService.getInstance().initConstructor();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException |
                InvocationTargetException | FileNotFoundException e) {
            log.log(Level.SEVERE, "Error when trying to set dependency!");
            System.exit(0);
        }
    }
}
