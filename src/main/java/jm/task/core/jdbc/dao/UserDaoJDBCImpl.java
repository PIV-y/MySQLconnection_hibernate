package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = null;

    public UserDaoJDBCImpl() {
        connection = getMySQLConnection();
    }

    public void createUsersTable() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS `testkata`.`users` (" +
                "`id` BIGINT(255) NOT NULL AUTO_INCREMENT, " +
                "`name` VARCHAR(45) NULL , " +
                "`lastName` VARCHAR(45) NULL, " +
                "`age` TINYINT NULL, " +
                "PRIMARY KEY (`id`));";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateTable);
            System.out.println("table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String sqlCreateTable = "DROP TABLE IF EXISTS testkata.users;";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateTable);
            System.out.println("table deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO testkata.users (name, lastName, age) VALUES (?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            System.out.println("User has been added.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void removeUserById(long id) {
    }
    public List<User> getAllUsers() {
        PreparedStatement preparedStatement = null;
        List<User> users = new LinkedList<>();
        String sql = "SELECT * FROM test-for-kata";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void cleanUsersTable() {

    }
}
