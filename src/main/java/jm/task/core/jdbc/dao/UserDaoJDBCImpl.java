package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = null;

    public UserDaoJDBCImpl() {
        connection = getMySQLConnection();
    }

    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS testkata.users (" +
                "`id` BIGINT(255) NOT NULL, " +
                "`name` VARCHAR(45) NULL , " +
                "`lastName` VARCHAR (45) NULL, " +
                "`age` INT NULL, " +
                "PRIMARY KEY (`id`));";

        try {
            preparedStatement = connection.prepareStatement(sqlCreateTable);
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
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sqlCreateTable = "DROP TABLE IF EXISTS testkata.users;";

        try {
            preparedStatement = connection.prepareStatement(sqlCreateTable);
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
    }

    public void saveUser(String name, String lastName, byte age) {

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
