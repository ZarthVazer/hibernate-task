package edu.epam.fop.web.jpa;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Adilov Abdubosit
 */
public final class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            Flyway.configure()
                    .dataSource(configuration.getProperty("hibernate.connection.url"),
                            configuration.getProperty("hibernate.connection.username"),
                            configuration.getProperty("hibernate.connection.password"))
                    .load()
                    .migrate();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static synchronized void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }
}
