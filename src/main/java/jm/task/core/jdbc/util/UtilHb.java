package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilHb {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
