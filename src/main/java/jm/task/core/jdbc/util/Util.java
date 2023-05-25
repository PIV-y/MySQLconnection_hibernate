package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionURL = "jdbc:mysql://127.0.0.1:3306/testkata";
    // Connect to MySQL
    public static Connection getMySQLConnection() {
        Connection connection = null;
        // Подключение к БД
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded success");
            connection = DriverManager.getConnection(connectionURL, userName, password);
            System.out.println("Connection Ok.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}