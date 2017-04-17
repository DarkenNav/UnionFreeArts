package ru.unionfreearts.webapp.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = configureSessionFactory();

    /**
     * Создание фабрики
     * @return {@link SessionFactory}
     * @throws HibernateException
     */
    private static SessionFactory configureSessionFactory()
            throws HibernateException {
        File hibernateConfig = new File("src/main/resources/config/hibernate.cfg.xml");
        Configuration configuration = new Configuration().configure(hibernateConfig);
        return configuration.buildSessionFactory();
    }

    /**
     * Получить фабрику сессий
     * @return {@link SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
