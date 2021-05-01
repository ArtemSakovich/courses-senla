package com.company.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.company")
@EnableTransactionManagement
public class AnnotationAppConfig {
    @Value("${connectionUrl}")
    private String CONNECTION_URL;
    @Value("${modelPackageToScan}")
    private String modelPackageToScan;
    private LocalContainerEntityManagerFactoryBean emf;
    private DriverManagerDataSource dataSource;
    private JpaTransactionManager tm;
    @Bean
    public DataSource dataSource() {
        if (dataSource == null) {
            dataSource = new DriverManagerDataSource();
            dataSource.setUrl(CONNECTION_URL);
        }
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        if (emf == null) {
            emf = new LocalContainerEntityManagerFactoryBean();
            emf.setDataSource(dataSource());
            emf.setPackagesToScan(modelPackageToScan);

            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            emf.setJpaVendorAdapter(vendorAdapter);
            emf.setJpaProperties(additionalProperties());
        }
        return emf;
    }

    @Bean()
    public PlatformTransactionManager transactionManager() {
        if (tm == null) {
            tm = new JpaTransactionManager();
            tm.setEntityManagerFactory(entityManagerFactory().getNativeEntityManagerFactory());
            tm.setDataSource(dataSource());
        }
        return tm;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");

        return properties;
    }
}
