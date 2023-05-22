package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // Connect to MySQL
    public static Connection getMySQLConnection() {
        String hostName = "127.0.0.1";
        String dbName = "testkata";
        String userName = "root";
        String password = "root";

        try {
            return getMySQLConnection(hostName, dbName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {
        Connection connection = null;
        // Подключение к БД
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded success");
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            connection = DriverManager.getConnection(connectionURL, userName, password);
            System.out.println("Connection Ok.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Получение соединения
        return connection;
    }
}
