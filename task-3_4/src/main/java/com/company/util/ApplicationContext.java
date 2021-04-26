package com.company.util;

import com.company.injection.DependencyController;
import com.company.injection.DependencyService;

import java.util.Map;

public final class ApplicationContext {
    private final Map<String, Object> applicationContext;

    public ApplicationContext(Map<String, Object> applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static Map<String, Object> getApplicationContext(Class<?> clazz) {
        DependencyController.setDependency(clazz);
        return DependencyService.getInstance().getInstanceClassMap();
    }
}
