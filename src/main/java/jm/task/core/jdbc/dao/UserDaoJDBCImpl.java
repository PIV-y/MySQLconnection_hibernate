package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
  // Connection connection = null;
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS `testkata`.`users` (" +
                "`id` BIGINT(255) NOT NULL AUTO_INCREMENT, " +
                "`name` VARCHAR(45) NULL , " +
                "`lastName` VARCHAR(45) NULL, " +
                "`age` TINYINT NULL, " +
                "PRIMARY KEY (`id`));";
        try (Connection connection = getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateTable);
            System.out.println("table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dropUsersTable() {
        String sqlCreateTable = "DROP TABLE IF EXISTS testkata.users;";
        try (Connection connection = getMySQLConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateTable);
            System.out.println("table deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT IGNORE INTO `testkata`.`users` (`name`, `lastName`, `age`) VALUES (?,?,?)";
        try (Connection connection = getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User " + name + " " + lastName + " at the age of " + age + " has been added.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeUserById(long id) {
        String sql = "DELETE FROM `testkata`.`users` WHERE id = ?";
        try (Connection connection = getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User with id=" + id + " has been deleted.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getAllUsers() {
        ResultSet result;
        List<User> users = new LinkedList<>();
        String sql = "SELECT * FROM testkata.users";
        try (Connection connection = getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            result = statement.executeQuery();
            while (result.next()) {
                users.add(new User( result.getString(2),
                                    result.getString(3),
                                    result.getByte(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(users);
        return users;
    }
    public void cleanUsersTable() {
        String sql = "DELETE FROM testkata.users";
        try (Connection connection = getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            System.out.println("Table is clean.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
