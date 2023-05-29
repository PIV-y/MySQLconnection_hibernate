package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.UtilHb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
 //   Session session = UtilHb.getSession();
    private final String sqlCreateUsersTable = "CREATE TABLE IF NOT EXISTS `testkata`.`users` (" +
            "`id` BIGINT(255) NOT NULL AUTO_INCREMENT, " +
            "`name` VARCHAR(45) NULL , " +
            "`lastName` VARCHAR(45) NULL, " +
            "`age` TINYINT NULL, " +
            "PRIMARY KEY (`id`));";
    private final String sqlDropUsersTable = "DROP TABLE IF EXISTS testkata.users;";

    @Override
    public void createUsersTable() {
        try (Session ses = UtilHb.getSession()) {
            Transaction transaction = ses.beginTransaction();
            ses.createSQLQuery(sqlCreateUsersTable).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session ses = UtilHb.getSession()) {
            Transaction transaction = ses.beginTransaction();
            ses.createSQLQuery(sqlDropUsersTable).executeUpdate();
            transaction.commit();
            System.out.println("Table has been deleted.");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session ses = UtilHb.getSession()) {
            Transaction transaction = ses.beginTransaction();
            ses.persist(new User(name,lastName,age));
            transaction.commit();
        }
        System.out.println("User has been added.");
    }

    @Override
    public void removeUserById(long id) {
        try (Session ses = UtilHb.getSession()) {
            Transaction transaction = ses.beginTransaction();
            ses.remove(ses.get(User.class, id));
            transaction.commit();
        }
        System.out.println("User has been added.");
    }

    @Override
    public List<User> getAllUsers() {
        try (Session ses = UtilHb.getSession()) {
            return ses.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session ses = UtilHb.getSession()) {
            Transaction transaction = ses.beginTransaction();
            Query query = ses.createQuery("DELETE FROM User");
            query.executeUpdate();

            transaction.commit();
        }
        System.out.println("Table has been cleaned");
    }
}
