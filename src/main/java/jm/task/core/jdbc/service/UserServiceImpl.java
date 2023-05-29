package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl UserDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        UserDao.createUsersTable();
        System.out.println("Table has been created");
    }
    public void dropUsersTable() {
        UserDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        UserDao.removeUserById(id);
        System.out.println("User with " + id + " has been removed.");
    }

    public List<User> getAllUsers() {
        return UserDao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDao.cleanUsersTable();
    }
}
