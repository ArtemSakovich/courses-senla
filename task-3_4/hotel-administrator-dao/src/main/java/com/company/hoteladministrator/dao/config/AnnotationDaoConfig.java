package com.company.hoteladministrator.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:dao.properties"})
@ComponentScan(basePackages = "com.company.hoteladministrator")
@EnableTransactionManagement
public class AnnotationDaoConfig {
    @Value("${connectionUrl}")
    private String CONNECTION_URL;
    @Value("${modelPackageToScan}")
    private String modelPackageToScan;
    @Value("${hibernateHbm2ddlAutoProperty:validate}")
    private String hibernateHbm2ddlAutoProperty;
    @Value("${hibernateDialectProperty:org.hibernate.dialect.PostgreSQL9Dialect}")
    private String hibernateDialectProperty;
    private LocalContainerEntityManagerFactoryBean emf;
    private SharedEntityManagerBean entityManagerBean;
    private DriverManagerDataSource dataSource;
    private JpaTransactionManager tm;

    @Bean
    public DataSource dataSource() {
        if (dataSource == null) {
            dataSource = new DriverManagerDataSource();
            dataSource.setUrl(CONNECTION_URL);
            dataSource.setDriverClassName("org.postgresql.Driver");
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
            tm.setEntityManagerFactory(entityManagerFactory().getObject());
            tm.setDataSource(dataSource());
        }
        return tm;
    }

    @Bean
    public SharedEntityManagerBean entityManager() {
        if (entityManagerBean == null) {
            entityManagerBean = new SharedEntityManagerBean();
            entityManagerBean.setEntityManagerFactory(entityManagerFactory().getObject());
        }
        return entityManagerBean;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAutoProperty);
        properties.setProperty("hibernate.dialect", hibernateDialectProperty);
        return properties;
    }
}
