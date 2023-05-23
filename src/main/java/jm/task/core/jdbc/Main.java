package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    //    userDaoJDBC.createUsersTable();
    //    userDaoJDBC.dropUsersTable();
        userDaoJDBC.saveUser("Boby", "Benet", (byte)27);
    }
}
