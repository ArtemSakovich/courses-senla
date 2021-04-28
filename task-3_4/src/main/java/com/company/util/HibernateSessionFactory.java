package com.company.util;

import com.company.injection.annotation.DependencyClass;
import com.company.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
@DependencyClass
public class HibernateSessionFactory {

    private SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    private SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Guest.class);
                configuration.addAnnotatedClass(Maintenance.class);
                configuration.addAnnotatedClass(Room.class);
                configuration.addAnnotatedClass(RoomAssignment.class);
                configuration.addAnnotatedClass(OrderedMaintenance.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public Session openSession() {
        return getSessionFactory().openSession();
    }
}