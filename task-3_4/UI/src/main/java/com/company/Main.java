package com.company;

import com.company.config.AnnotationAppConfig;
import com.company.menu.MenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnotationAppConfig.class);
        MenuController menuController = applicationContext.getBean(MenuController.class);
        menuController.run();
    }
}
