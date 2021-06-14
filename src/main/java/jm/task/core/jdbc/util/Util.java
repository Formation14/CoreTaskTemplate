package jm.task.core.jdbc.util;



import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static SessionFactory sessionFactory;
    private static Util instance = null;
    public synchronized static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb?autoReconnect=true" +
                        "&useSSL=FALSE" +
                        "&useLegacyDatetimeCode=false" +
                        "&serverTimezone=UTC" +
                        "&allowPublicKeyRetrieval=true");
                settings.put(Environment.USER, "Pavel");
                settings.put(Environment.PASS, "1111");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}