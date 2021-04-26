package com.company.injection;

import com.company.configuration.ConfigService;
import com.company.injection.annotation.DependencyClass;
import com.company.injection.annotation.DependencyComponent;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DependencyService {
    private static DependencyService instance;
    private final Map<String, Object> instanceClassMap;
    private Object instanceClass;

    private DependencyService() {
        this.instanceClassMap = new HashMap<>();
    }

    public static DependencyService getInstance() {
        if (instance == null) {
            instance = new DependencyService();
        }
        return instance;
    }

    public void setVariable(Class<?> clazz) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException, FileNotFoundException {
        if (clazz.getAnnotation(DependencyClass.class) == null) {
            throw new IllegalArgumentException("Class " + clazz.getSimpleName() + " don't have 'DependencyClass' annotation");
        }
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        this.instanceClass = constructor.newInstance();
        ConfigService.getInstance().setValue(instanceClass);
        constructor.setAccessible(false);
        instanceClassMap.put(clazz.getName(), instanceClass);
    }

    public void initConstructor() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, FileNotFoundException {
        final List<Field> annotatedFields = getAnnotatedFields();
        for (Field field : annotatedFields) {
            final Class<?> implClass = DependencyInject.getInstance().injection(field);
            if (implClass.isAnnotationPresent(DependencyClass.class) && !instanceClassMap.containsKey(implClass.getName())) {
                final Object bufInstanceClass = this.instanceClass;
                setVariable(implClass);
                initConstructor();
                this.instanceClass = bufInstanceClass;
            }
            field.setAccessible(true);
            if (instanceClassMap.containsKey(implClass.getName())) {
                field.set(instanceClass, instanceClassMap.get(implClass.getName()));
                field.setAccessible(false);
            }
        }
    }

    private List<Field> getAnnotatedFields() {
        return Arrays.stream(instanceClass.getClass().getDeclaredFields())
                .filter(i -> !i.getType().isPrimitive() && i.isAnnotationPresent(DependencyComponent.class))
                .collect(Collectors.toList());
    }

    public Map<String, Object> getInstanceClassMap() {
        return instanceClassMap;
    }
}