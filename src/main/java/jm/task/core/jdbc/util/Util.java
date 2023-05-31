package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/testkata";
    private static SessionFactory sessionFactory;

    public static Connection getMySQLConnection() {
        Connection connection = null;
        // Подключение к БД
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded success");
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            System.out.println("Connection Ok.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Session getSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", CONNECTION_URL);
                configuration.setProperty("hibernate.connection.username", USER_NAME);
                configuration.setProperty("hibernate.connection.password", PASSWORD);

                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Getting session done");
        return sessionFactory.openSession();
    }
}